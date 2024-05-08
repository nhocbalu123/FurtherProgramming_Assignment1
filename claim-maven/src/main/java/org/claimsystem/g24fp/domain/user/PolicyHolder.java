package org.claimsystem.g24fp.domain.user;

import java.util.ArrayList;
import java.util.List;

public class PolicyHolder extends Customer {
    private String policyOwnerID;
    private String cardNum;
    private List<Dependent> dependents;

    // Constructor
    public PolicyHolder() {
        super();
    }
    public PolicyHolder(Customer c, String pOwnerID, String cardNum) {
        super(c.getUsername(), c.getCustomerID(), c.getCustomerName(), c.getBankAcc());
        this.policyOwnerID = pOwnerID;
        this.cardNum = cardNum;
        this.dependents = new ArrayList<>();
    }

    public PolicyHolder(String username, String customerID, String customerName, String bankAcc, String policyOwnerID, String cardNum) {
        super(username, customerID, customerName, bankAcc);
        this.policyOwnerID = policyOwnerID;
        this.cardNum = cardNum;
        this.dependents = new ArrayList<>();
    }


    // DEPENDENT PROCESS
    public List<Dependent> getDependents() {
        return this.dependents;
    }
    public String getPolicyOwnerID() {
        return policyOwnerID;
    }

    public void setPolicyOwnerID(String policyOwnerID) {
        this.policyOwnerID = policyOwnerID;
    }

    public String getCardNum() {
        return cardNum;
    }
    public void setCard(String card) {
        this.cardNum = card;
    }
    @Override
    public String toString() {
        return "PolicyHolder{" + super.toString() +
                "policyOwnerID='" + policyOwnerID + '\'' +
                ", dependents=" + dependents +
                ", cardNum='" + cardNum + '\''+
                '}';
    }

}
