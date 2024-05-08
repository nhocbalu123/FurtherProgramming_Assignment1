package org.claimsystem.g24fp.logic;

import org.claimsystem.g24fp.domain.InsuranceCard;
import org.claimsystem.g24fp.domain.user.PolicyHolder;
import org.claimsystem.g24fp.domain.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ICardDB implements IProcessManager<InsuranceCard>{

    @Override
    public void add(Connection conn, Map<String, String> records) {
        String insertSQL = "INSERT INTO insurance_card(id, card_holder, expiration_date) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setString(1, records.get("card_num"));
            ps.setString(2, records.get("id")); // card_holder id
            ps.setDate(3, java.sql.Date.valueOf(records.get("expiration_date")));
            ps.executeUpdate();
            System.out.println("Insurance Card added successfully!");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    @Override
    public void update(Connection conn, Map<String, String> updateField) {
        // update card_holder only by card_num
        String updateSQL = "UPDATE insurance_card SET " + updateField.get("column") + " = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            ps.setString(1, updateField.get("card_holder"));
            ps.setString(2, updateField.get("card_num"));
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }
    }


    @Override
    public void delete(Connection conn, String cardNum) {
        String deleteSQL = "DELETE FROM insurance_card WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteSQL);
            ps.setString(1, cardNum);
            ps.executeUpdate();
            System.out.println("Insurance Card deleted successfully!");
        } catch (Exception e) {
            System.err.println(e.getClass().getName());
        }
    }

    @Override
    public InsuranceCard getOne(Connection conn, String id) {
        String selectOne = "SELECT * FROM insurance_card WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(selectOne)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PolicyHolder searchHolder = (new UserDB<PolicyHolder>()).getOne(conn, rs.getString("card_holder"));
                InsuranceCard searchCard = new InsuranceCard(rs.getString("id"), searchHolder, rs.getDate("expiration_date").toLocalDate());
                System.out.println("Insurance Card found!");
                return searchCard;
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<InsuranceCard> getAll(Connection conn) {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        String selectAll = "SELECT * FROM insurance_card";
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(selectAll);
            while (rs.next()) {
                PolicyHolder searchHolder = (new UserDB<PolicyHolder>()).getOne(conn, rs.getString("card_holder"));
                InsuranceCard searchCard = new InsuranceCard(rs.getString("id"), searchHolder, rs.getDate("expiration_date").toLocalDate());
                insuranceCards.add(searchCard);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return insuranceCards;
    }
}
