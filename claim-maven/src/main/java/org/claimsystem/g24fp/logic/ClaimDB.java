package org.claimsystem.g24fp.logic;

import org.claimsystem.g24fp.domain.Document;
import org.claimsystem.g24fp.domain.Claim;
import org.claimsystem.g24fp.domain.Policy;
import org.claimsystem.g24fp.domain.user.PolicyHolder;
import org.claimsystem.g24fp.domain.user.Provider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ClaimDB implements IProcessManager<Claim>{
    @Override
    public void add(Connection conn, Map<String, String> record) {
        String insertQuery = "insert into claim(id, insured_person, request_amount, applied_policy, claim_status, claim_amount, process_by) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            // calculate claim amount
            double claimAmount = calClaimAmount(conn, Double.parseDouble(record.get("request_amount")), record.get("applied_policy"));
            ps.setString(1, record.get("id"));
            ps.setString(2, record.get("insured_person"));
            ps.setDouble(3, Double.parseDouble(record.get("request_amount")));
            ps.setString(4, record.get("applied_policy"));
            // time is automatically set to current time
            ps.setString(5, record.get("claim_status"));
            ps.setDouble(6, claimAmount);
            ps.setString(7, record.get("process_by"));
            ps.executeUpdate();
            System.out.println("Claim add successfully!");
    } catch (SQLException e) {
        System.err.println("Error: " + e);
    }}

    private double calClaimAmount(Connection conn, double requestAmount, String appliedPolicy) {
// calculate claim amount based on request amount and applied policy
        Policy policy = new PolicyDB().getOne(conn,appliedPolicy);
        return requestAmount * policy.getCoverRate();
    }

    @Override
    public void update(Connection conn, Map<String, String> updateField) {
        // update claim in the database by claimID
        String updateQuery = "UPDATE claim SET " + updateField.get("column") + " = ? WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            switch (updateField.get("column")) {
                case "request_amount":
                    ps.setDouble(1, Double.parseDouble(updateField.get("value")));
                    break;
                case "claim_status", "process_by":
                    ps.setString(1, updateField.get("value"));
                    break;
                case "exam_date":
                    ps.setTimestamp(1, java.sql.Timestamp.valueOf(updateField.get("value")));
                    break;
                default:
                    System.out.println("Cannot update this column!");
            }
            ps.setString(2, updateField.get("id"));
            ps.executeUpdate();
            System.out.println("Claim updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    @Override
    public void delete(Connection conn, String claimID) {
        // delete claim in the database by claimID
        String deleteQuery = "DELETE FROM claim WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteQuery);
            ps.setString(1, claimID);
            ps.executeUpdate();
            System.out.println("Claim deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    @Override
    public Claim getOne(Connection conn, String id) {
        // search claim by id
        Claim claim = new Claim();
        String selectQuery = "SELECT * FROM claim WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(selectQuery)) {
            ps.setString(1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                fetchData(conn, rs, claim);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claim;

    }

    @Override
    public List<Claim> getAll(Connection conn) {
        List<Claim> claims = new ArrayList<>();
        String selectAll = "SELECT * FROM claim";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)) {
            while (resultSet.next()) {
                Claim claim = new Claim();
                fetchData(conn, resultSet, claim);
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }

    private void fetchData(Connection conn, ResultSet resultSet, Claim claim) throws SQLException {
        claim.setId(resultSet.getString("id"));
        claim.setInsuredPerson((new UserDB<PolicyHolder>()).getOne(conn, resultSet.getString("insured_person")));
        claim.setRequestAmount(resultSet.getDouble("request_amount"));
        claim.setAppliedPolicy((new PolicyDB()).getOne(conn, resultSet.getString("applied_policy")));
        claim.setClaimDate(resultSet.getTimestamp("claim_date").toLocalDateTime());
        claim.setExamDate(resultSet.getTimestamp("exam_date").toLocalDateTime());
        claim.setClaimStatus(Claim.ClaimStatus.valueOf(resultSet.getString("claim_status").toUpperCase()));
        claim.setClaimAmount(resultSet.getDouble("claim_amount"));
        claim.setProvider((new UserDB<Provider>()).getOne(conn, resultSet.getString("process_by")));
    }

    // handle document
    public void addDocument(Connection conn, Map<String, String> record, byte[] imageBytes) {
        String insertQuery = "INSERT INTO document(id, docName, image, claimID) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(insertQuery);
            ps.setString(1, record.get("id"));
            ps.setString(2, record.get("name"));
            ps.setBytes(3, imageBytes); // Set the image field
            ps.setString(4, record.get("claimID"));
            ps.executeUpdate();
            System.out.println("Document added successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    public void updateDocument(Connection conn, Map<String, String> updateField, byte[] imageBytes) {
        // update document in the database by docID
        String updateQuery = "UPDATE document SET " + updateField.get("column") + " = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(updateQuery);
            switch (updateField.get("column")) {
                case "docName":
                    ps.setString(1, updateField.get("value"));
                    break;
                case "image":
                    ps.setBytes(1, imageBytes);
                    break;
                default:
                    System.out.println("Cannot update this column!");
            }
            ps.setString(2, updateField.get("id"));
            ps.executeUpdate();
            System.out.println("Document updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    public void deleteDocument(Connection conn, String id) {
        // delete document in the database by docID
        String deleteQuery = "DELETE FROM document WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(deleteQuery);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Document deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }

    public Document getDocument(Connection conn, String id) {
        // search document by docID
        Document document = new Document();
        String selectQuery = "SELECT * FROM document WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(selectQuery)) {
            ps.setString(1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                document.setId(rs.getString("id"));
                document.setName(rs.getString("docName"));
                document.setImage(rs.getBytes("image"));
                document.setClaimID(rs.getString("claimID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return document;
    }

    public List<Document> getAllDocuments(Connection conn) {
        List<Document> documents = new ArrayList<>();
        String selectAll = "SELECT * FROM document";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)) {
            while (resultSet.next()) {
                Document document = new Document();
                document.setId(resultSet.getString("id"));
                document.setName(resultSet.getString("docName"));
                document.setImage(resultSet.getBytes("image"));
                document.setClaimID(resultSet.getString("claimID"));
                documents.add(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

}
