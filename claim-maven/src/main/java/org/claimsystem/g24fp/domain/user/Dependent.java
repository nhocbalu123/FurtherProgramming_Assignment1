package org.claimsystem.g24fp.domain.user;

public class Dependent extends Customer{
    private String relationship;
    private String policyHolderID;
    private String cardNum;

    public Dependent() {
        super();
    }

    public Dependent(Customer c, String policyHolderID, String relationship, String cardNum) {
        super(c.getUsername(), c.getCustomerID(), c.getCustomerName(), c.getBankAcc());
        this.policyHolderID = policyHolderID;
        this.relationship = relationship;
        this.cardNum = cardNum;
    }

    public String getPolicyHolderID() {
        return policyHolderID;
    }

    public void setPolicyHolderID(String policyHolderID) {
        this.policyHolderID = policyHolderID;
    }

    @Override
    public String toString() {
        return "Dependent{" + super.toString() +
                "relationship='" + relationship + '\'' +
                ", policyHolderID='" + policyHolderID + '\'' +
                ", cardNum='" + cardNum + '\'' +
                '}';
    }
}
