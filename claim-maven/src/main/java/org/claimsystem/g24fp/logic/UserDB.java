package org.claimsystem.g24fp.logic;


import org.claimsystem.g24fp.domain.Bank;
import org.claimsystem.g24fp.domain.user.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDB<U extends User> implements IProcessManager<U>{
    @Override
    public void add(Connection conn, Map<String, String> records) {
        // add user account
        String insertSQL = "INSERT INTO user_info (id, password) VALUES (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setString(1, records.get("user_name"));
            ps.setString(2, records.get("password"));
            ps.executeUpdate();
            System.out.println("User added successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        // add customer or provider base on the user_id
        if (records.get("id").startsWith("c")) {
            String insertCustomer = "INSERT INTO customer (id, cust_name, cust_type, user_name, policy_owner, policy_holder, dep_relationship) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(insertCustomer);
                ps.setString(1, records.get("id"));
                ps.setString(2, records.get("name"));
                ps.setString(3, records.get("type"));
                ps.setString(4, records.get("user_name"));
                ps.setString(5, records.get("policy_owner"));
                ps.setString(6, records.get("policy_holder")); // dependent account will have this field
                ps.setString(7, records.get("dep_relationship")); // dependent account will have this field
                ps.executeUpdate();
                System.out.println("Customer added successfully!");
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        } else if (records.get("id").startsWith("p")) {
            String insertProvider = "INSERT INTO provider (id, prov_name, position, manager, user_name) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(insertProvider);
                ps.setString(1, records.get("id"));
                ps.setString(2, records.get("name"));
                ps.setString(3, records.get("position"));
                ps.setString(4, records.get("manager"));
                ps.setString(5, records.get("user_name"));
                System.out.println("Provider added successfully!");
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
    }
    @Override
    public void update(Connection conn, Map<String, String> updateFields) {
        // update specific fields by user id
        // only admin can update user info
        boolean isAdmin = updateFields.get("admin").startsWith("s");
        if (isAdmin) {
            String updateSQL = "UPDATE user_info SET " + updateFields.get("column") + " = ? WHERE id in (select user_name from customer where customer.id = ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(updateSQL);
                ps.setString(1, updateFields.get("value"));
                ps.setString(2, updateFields.get("id"));
                ps.executeUpdate();
                System.out.println("Record updated successfully!");
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
            return;
        }
        String updateSQL = "";
        String id = updateFields.get("id");
        if (id.startsWith("c")) {
            updateSQL = "UPDATE customer SET " + updateFields.get("column") + " = ? WHERE id = ?";
        } else if (id.startsWith("p")) {
            updateSQL = "UPDATE provider SET " + updateFields.get("column") + " = ? WHERE id = ?";
        }
        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            ps.setString(1, updateFields.get("value"));
            ps.setString(2, id);
            ps.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    @Override
    public void delete(Connection conn, String id) {
        // delete user by user id
        // delete normal user means delete from user_info table
        // if the user has an insurance card, delete from insurance_card table
        // ìf the user has a bank account, delete from bank table
        String deleteUserSQL = "DELETE FROM user_info WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteUserSQL);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Record deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

    }


    public U getOne(Connection conn, String userID) {
        // if user is a customer get customer details from the customer table
        // if user is a provider get provider details from the provider table
        String selectOne;
        try {
            if (userID.startsWith("c")) {
                selectOne = "select c.*, ic.id as card_num, b.id as bankAccNumber from customer c left join insurance_card ic on c.id = ic.card_holder left join bank b on c.id = b.customer_id where c.id = ?;";
                PreparedStatement ps = conn.prepareStatement(selectOne);
                ps.setString(1, userID);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    return null;
                }
                String custID = rs.getString("id");
                String custName = rs.getString("cust_name");
                String username = rs.getString("user_name");
                String policyOwner = rs.getString("policy_owner");
                String custType = rs.getString("cust_type");
                String cardNum = rs.getString("card_num");
                String bankAccNumber = rs.getString("bankAccNumber");
                String relationship = rs.getString("dep_relationship");
                String policyHolder = rs.getString("policy_holder");
                Customer customer = new Customer(username, custID, custName, bankAccNumber);
                if (custType.equals("policy owner")) {
                    customer = new PolicyOwner(customer);
                } else if (custType.equals("policy holder")) {
                    customer = new PolicyHolder(customer, policyOwner, cardNum);
                } else if (custType.equals("dependent")) {
                    customer = new Dependent(customer, relationship, policyHolder, cardNum);
                }
                return (U) customer;
            } else if (userID.startsWith("p")) {
                selectOne = "SELECT * FROM provider WHERE id = ?;";
                PreparedStatement ps = conn.prepareStatement(selectOne);
                ps.setString(1, userID);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    return null;
                }
                String provID = rs.getString("id");
                String provName = rs.getString("prov_name");
                String username = rs.getString("user_name");
                String position = rs.getString("position");
                String manager = rs.getString("manager");
                Provider provider = new Provider(username, provID, provName);
                if (position.equals("insurance surveyor")) {
                    provider = new ISurveyor(provider, manager);
                } else {
                    provider = new IManager(provider);
                }
                return (U) provider;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null; // Return null if no user found
    }


    @Override
    public List<U> getAll(Connection conn) {
        List<U> users = new ArrayList<>();
        try {
            // Get all customers
            String selectCustomers = "select c.*, ic.id as card_num, b.id as bankAccNumber from customer c left join insurance_card ic on c.id = ic.card_holder left join bank b on c.id = b.customer_id;";
            PreparedStatement ps = conn.prepareStatement(selectCustomers);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String custID = rs.getString("id");
                String custName = rs.getString("cust_name");
                String username = rs.getString("user_name");
                String policyOwner = rs.getString("policy_owner");
                String custType = rs.getString("cust_type");
                String cardNum = rs.getString("card_num");
                String bankAccNumber = rs.getString("bankAccNumber");
                Customer customer = new Customer(username, custID, custName, bankAccNumber);
                String relationship = rs.getString("dep_relationship");
                String policyHolder = rs.getString("policy_holder");
                if (custType.equals("policy owner")) {
                    customer = new PolicyOwner(customer);
                } else if (custType.equals("policy holder")) {
                    customer = new PolicyHolder(customer, policyOwner, cardNum);
                } else {
                    customer = new Dependent(customer, relationship, policyHolder, cardNum);
                }
                users.add((U) customer);
            }

            // Get all providers
            String selectProviders = "SELECT * FROM provider";
            ps = conn.prepareStatement(selectProviders);
            rs = ps.executeQuery();
            while (rs.next()) {
                String provID = rs.getString("id");
                String provName = rs.getString("prov_name");
                String username = rs.getString("user_name");
                String position = rs.getString("position");
                String manager = rs.getString("manager");
                Provider provider = new Provider(username, provID, provName);
                if (position.equals("insurance surveyor")) {
                    provider = new ISurveyor(provider, manager);
                } else {
                    provider = new IManager(provider);
                }
                users.add((U) provider);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return users;
    }

    // HANDLE DEPENDENCIES OF POLICYHOLDER


}
