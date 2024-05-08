package org.claimsystem.g24fp.domain.user;

public class ISurveyor extends Provider {
    private String managerID;

    public ISurveyor() {
        super();
        this.managerID = "default";
    }

    public ISurveyor(Provider p, String managerID) {
        super(p.getUsername(), p.getProviderID(), p.getProviderName());
        this.managerID = managerID;
    }

    @Override
    public void viewInfo() {
        System.out.println("SURVEYOR INFO: ");
        super.viewInfo();
        System.out.println("Manager ID: " + managerID);
    }

    @Override
    public String toString() {
        return "ISurveyor{" + super.toString() +
                "managerID='" + managerID + '\'' +
                '}';
    }

    // getter and setter
    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
}
