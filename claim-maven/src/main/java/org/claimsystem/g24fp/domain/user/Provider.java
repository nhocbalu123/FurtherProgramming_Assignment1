package org.claimsystem.g24fp.domain.user;

public class Provider extends User{

    private String providerID;
    private String providerName;

    public Provider() {
        super();
        this.providerID = "default";
        this.providerName = "default";
    }

    public Provider(String username, String providerID, String providerName) {
        super(username);
        this.providerID = providerID;
        this.providerName = providerName;
    }

    @Override
    public void viewInfo() {
        System.out.println("PROVIDER INFO: ");
        super.viewInfo();
        System.out.println("Provider ID: " + providerID);
        System.out.println("Provider Name: " + providerName);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "providerID='" + providerID + '\'' +
                ", providerName='" + providerName + '\'' +
                '}';
    }


    // getter and setter

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}

