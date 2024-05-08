package org.claimsystem.g24fp.domain.user;

import org.claimsystem.g24fp.domain.Policy;
import org.claimsystem.g24fp.logic.DBConnection;
import org.claimsystem.g24fp.logic.UserDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PolicyOwner extends Customer {
    private List<Policy> policies;

    public PolicyOwner(Customer c) {
        super(c.getUsername(), c.getCustomerID(), c.getCustomerName(), c.getBankAcc());
        this.policies = new ArrayList<>();
    }

    public List<Policy> getPolicies() {
        return policies;
    } // or get from database

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    // SEND CLAIMS TO PROVIDER
    @Override
    public String toString() {
        return "PolicyOwner{" + super.toString() +
                "policies=" + policies +
                '}';
    }
}
