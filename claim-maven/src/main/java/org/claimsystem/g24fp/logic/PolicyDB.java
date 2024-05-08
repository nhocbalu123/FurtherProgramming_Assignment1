package org.claimsystem.g24fp.logic;
import org.claimsystem.g24fp.domain.Claim;
import org.claimsystem.g24fp.domain.Policy;
import org.claimsystem.g24fp.domain.user.Customer;
import org.claimsystem.g24fp.domain.user.PolicyOwner;
import org.claimsystem.g24fp.domain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PolicyDB implements IProcessManager<Policy>{


    @Override
    public void add(Connection conn, Map<String, String> info) {
        String insertSQL = "INSERT INTO policy(id, policy_name, policy_type, policy_content, cover_rate, policy_owner) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setString(1, info.get("id"));
            ps.setString(2, info.get("name"));
            ps.setString(3, info.get("type"));
            ps.setString(4, info.get("content"));
            ps.setDouble(5, Double.parseDouble(info.get("cover_rate")));
            ps.setString(6, info.get("policy_owner"));
            ps.executeUpdate();
            System.out.println("Policy added successfully!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }
    }

    @Override
    public void update(Connection conn, Map<String, String> info) {
        String updateSQL = "UPDATE policy SET " + info.get("column") + " = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            switch (info.get("column")) {
                case "policy_name", "policy_type", "policy_content", "policy_owner":
                    ps.setString(1, info.get("value"));
                    break;
                case "cover_rate":
                    ps.setDouble(1, Double.parseDouble(info.get("value")));
                    break;
                default:
                    System.out.println("Cannot update this column!");
            }
            ps.setString(2, info.get("id"));
            ps.executeUpdate();
            System.out.println("Policy updated successfully!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }
    }

    @Override
    public void delete(Connection conn, String id) {
        String deleteSQL = "DELETE FROM policy WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteSQL);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Policy deleted successfully!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }

    }

    @Override
    public Policy getOne(Connection conn, String id) {
        // search policy by id
        String getOne = "SELECT * FROM policy WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(getOne);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Policy searchPolicy = new Policy(rs.getString("id"), rs.getString("policy_name"), rs.getString("policy_type"), rs.getString("policy_content"), rs.getDouble("cover_rate"));
                searchPolicy.setPolicyOwner((new UserDB<PolicyOwner>()).getOne(conn, rs.getString("policy_owner")));
                return searchPolicy;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public List<Policy> getAll(Connection conn) {
        String getAll = "SELECT * FROM policy";
        List<Policy> policies = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(getAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Policy searchPolicy = new Policy(rs.getString("id"), rs.getString("policy_name"), rs.getString("policy_type"), rs.getString("policy_content"), rs.getDouble("cover_rate"));
                searchPolicy.setPolicyOwner((new UserDB<PolicyOwner>()).getOne(conn, rs.getString("policy_owner")));
                policies.add(searchPolicy);
            }
            System.out.println("All policies found!");
            return policies;
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }
        return List.of();
    }
}
