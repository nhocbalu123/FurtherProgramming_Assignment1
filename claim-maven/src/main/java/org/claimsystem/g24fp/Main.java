package org.claimsystem.g24fp;

import org.claimsystem.g24fp.domain.Claim;
import org.claimsystem.g24fp.domain.Policy;
import org.claimsystem.g24fp.domain.user.Customer;
import org.claimsystem.g24fp.domain.user.PolicyHolder;
import org.claimsystem.g24fp.domain.user.User;
import org.claimsystem.g24fp.logic.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Initialize the DAOs
        UserDB<User> userDB = new UserDB<>();
        ClaimDB claimDB = new ClaimDB();
        PolicyDB policyDB = new PolicyDB();
        BankDB bankDB = new BankDB();
        ICardDB icardDB = new ICardDB();
        Connection conn = DBConnection.getConnection();



        // -----TEST USER
        // create new customer: must create user account first + bank info
        // user info: user_name, password
        // bank info: bankAccNumber, bankAccName, bankName, balance
        // customer info: customer_id, customer_name, cust_type, policy_owner, bankAccNumber
        // new policy holder: must create user account first + bank info + insurance card
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("user_name", "pholder4"); // user info
        userInfo.put("password", "12345");
        userInfo.put("id", "c1234570"); // customer info
        userInfo.put("name", "Nguyen Van A");
        userInfo.put("type", "policy holder");
        userInfo.put("policy_owner", "c7654322");
        userInfo.put("bankAccNumber", "4638563856"); // bank info
        userInfo.put("bankAccName", "Nguyen Van A");
        userInfo.put("bankName", "Vietcombank");
        userInfo.put("balance", "1000000");
        userInfo.put("card_num", "8492735024"); // insurance card info
        userInfo.put("expiration_date", "2024-12-31");
//        userDB.add(conn, userInfo);
//        bankDB.add(conn, userInfo);
//        icardDB.add(conn, userInfo);

//        userDB.getOne(conn, "c1234570").viewInfo();
//        userDB.delete(conn, "pholder4");

        // new policy owner: must create user account first + bank info
        Map<String, String> newPOwner = new HashMap<>();
        newPOwner.put("user_name", "powner3");
        newPOwner.put("password", "12345");
        newPOwner.put("id", "c7654323");
        newPOwner.put("name", "BKAV");
        newPOwner.put("type", "policy owner");
        newPOwner.put("policy_owner", null);
        newPOwner.put("bankAccNumber", "4938569957");
        newPOwner.put("bankAccName", "BKAV");
        newPOwner.put("bankName", "TPBank");
        newPOwner.put("balance", "900000099");
//        bankDB.add(conn, newPOwner);
//        userDB.add(conn, newPOwner);
        Map<String, String> userField = new HashMap<>();
        userField.put("admin", "s1122334"); // who is the admin
        userField.put("id", "c1234569"); // user_id to update
        userField.put("column", "password"); // update column
        userField.put("value", "hello123"); // new value
        userDB.update(conn, userField); // use DB class to update info

        // ----------TEST CLAIM---------
        // Create a new claim
        Map<String, String> newClaimInfo = new HashMap<>();
        newClaimInfo.put("id", "f1234567895"); // claim_id must be unique
        newClaimInfo.put("insured_person", "c1234569"); // policy_holder id
        newClaimInfo.put("request_amount", "2000"); // fill the request amount
        newClaimInfo.put("applied_policy", "p02"); // the policy applied for this claim
        newClaimInfo.put("claim_status", "new");
        newClaimInfo.put("process_by", "p1234568");


        Map<String, String> updateField = new HashMap<>();
        Map<String, String> providerField = new HashMap<>();
        updateField.put("id", "f1234567895"); // claim_id to update
        updateField.put("column", "claim_status"); // update column
        updateField.put("value", "done"); // new value

        providerField.put("id", "f1234567895"); // when the imanager process claim
        providerField.put("column", "process_by");
        providerField.put("value", "p7886543");
//        claimDB.add(conn, newClaimInfo);
//        claimDB.delete(conn, "f1234567895");
//        System.out.println("New claim added successfully!");

        Map<String, String> newClaimInfo1 = new HashMap<>();
        newClaimInfo1.put("id", "f1234567896"); // claim_id must be unique
        newClaimInfo1.put("insured_person", "c1234568"); // policy_holder id
        newClaimInfo1.put("request_amount", "5555"); // fill the request amount
        newClaimInfo1.put("applied_policy", "p01"); // the policy applied for this claim
        newClaimInfo1.put("claim_status", "new");
        newClaimInfo1.put("process_by", "p1234568");
//        claimDB.add(conn, newClaimInfo1);

        Map<String, String> newPolicy = new HashMap<>();
        newPolicy.put("id", "p03");
        newPolicy.put("name", "Health Insurance");
        newPolicy.put("type", "Health");
        newPolicy.put("content", "Health insurance policy for superman");
        newPolicy.put("cover_rate", "0.5");
        newPolicy.put("policy_owner", "c7654321");
//        policyDB.add(conn, newPolicy);

        Map<String, String> document = new HashMap<>();
        document.put("id", "d05");
        document.put("name", "document5");
        document.put("claimID", "f1234567896");
        claimDB.addDocument(conn, document, new byte[0]);
//        claimDB.deleteDocument(conn, "d05");

    }
}