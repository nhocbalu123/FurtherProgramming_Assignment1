package org.claimsystem.g24fp.logic;

import org.claimsystem.g24fp.domain.Bank;
import org.claimsystem.g24fp.domain.Policy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class BankDB implements IProcessManager<Bank>{

    @Override
    public void add(Connection conn, Map<String, String> info) {
        String insertSQL = "INSERT INTO bank(id, bankAccName, bankName, balance, customer_id) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setString(1, info.get("bankAccNumber"));
            ps.setString(2, info.get("bankAccName"));
            ps.setString(3, info.get("bankName"));
            ps.setDouble(4, Double.parseDouble(info.get("balance")));
            ps.setString(5, info.get("id"));
            ps.executeUpdate();
            System.out.println("Bank added successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    @Override
    public void update(Connection conn, Map<String, String> info) {
        // update bank in the database by bankAccNumber
        String updateSQL = "UPDATE bank SET " + info.get("column") + " = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            switch (info.get("column")) {
                case "bankAccName", "bankName":
                    ps.setString(1, info.get("value"));
                    break;
                case "balance":
                    ps.setDouble(1, Double.parseDouble(info.get("value")));
                    break;
                default:
                    System.out.println("Cannot update this column!");
            }
            ps.setString(2, info.get("id"));
            ps.executeUpdate();
            System.out.println("Bank updated successfully!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }
    }

    @Override
    public void delete(Connection conn, String id) {
        String deleteSQL = "DELETE FROM bank WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteSQL);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Bank deleted successfully!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }
    }
    @Override
    public Bank getOne(Connection conn, String id) {
        return null;
    }

    @Override
    public List<Bank> getAll(Connection conn) {
        return List.of();
    }
}
