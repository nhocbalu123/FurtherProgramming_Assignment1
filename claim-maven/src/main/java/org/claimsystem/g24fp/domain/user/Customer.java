package org.claimsystem.g24fp.domain.user;

import org.claimsystem.g24fp.domain.Claim;

import java.util.List;

public class Customer extends User {
    private String customerID;
    private String customerName;
    private String bankAcc;
    private List<Claim> claims;

    public Customer() {
        super();
        this.customerID = "default";
        this.customerName = "default";
    }

    public Customer(String customerID, String customerName) {
        super();
        this.customerID = customerID;
        this.customerName = customerName;
    }

    public Customer(String username, String customerID, String customerName, String bankAcc) {
        super(username);
        this.customerID = customerID;
        this.customerName = customerName;
        this.bankAcc = bankAcc;
    }

    public void viewInfo() {
        System.out.println("CUSTOMER INFO: ");
        super.viewInfo();
        System.out.println("Customer ID: " + customerID);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Bank Account: " + bankAcc);
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getBankAcc() {
        return bankAcc;
    }
    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", bankAcc=" + bankAcc +
                '}';
    }
}



