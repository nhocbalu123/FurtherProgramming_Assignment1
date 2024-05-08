package org.claimsystem.g24fp.domain;

import java.util.Arrays;

public class Document {
    private String id;
    private String name;
    private byte[] image;
    private String claimID;

    public Document() {
    }

    public Document(String id, String name, String claimID) {
        this.id = id;
        this.name = name;
        this.claimID = claimID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getClaimID() {
        return claimID;
    }

    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image=" + Arrays.toString(image) +
                ", claimID='" + claimID + '\'' +
                '}';
    }
}
