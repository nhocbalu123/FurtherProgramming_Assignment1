package org.claimsystem.g24fp.domain;
import org.claimsystem.g24fp.domain.user.PolicyHolder;
import org.claimsystem.g24fp.domain.user.Provider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Claim {
    private String id;
    private PolicyHolder insuredPerson;
    private double requestAmount;
    private Policy appliedPolicy;
    private LocalDateTime claimDate;
    private LocalDateTime examDate;
    private double claimAmount;
    private Provider provider;
    private List<String> documents;
    private ClaimStatus claimStatus;
    public enum ClaimStatus {
        NEW,
        PROCESSING,
        DONE
    }

    //Constructor
    public Claim() {}
    public Claim(String id, PolicyHolder insuredPerson, double requestAmount, Policy appliedPolicy, LocalDateTime claimDate, LocalDateTime examDate, double claimAmount, Provider provider, ClaimStatus claimStatus) {
        this.id = id;
        this.insuredPerson = insuredPerson;
        this.requestAmount = requestAmount;
        this.appliedPolicy = appliedPolicy;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        this.provider = provider;
        this.documents = new ArrayList<>();
        this.claimStatus = claimStatus;
    }

    //Getter

    public String getId() {
        return id;
    }

    public PolicyHolder getInsuredPerson() {
        return insuredPerson;
    }

    public double getRequestAmount() {
        return requestAmount;
    }

    public Policy getAppliedPolicy() {
        return appliedPolicy;
    }

    public LocalDateTime getClaimDate() {
        return claimDate;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public Provider getProvider() {
        return provider;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }


    //Setter


    public void setId(String id) {
        this.id = id;
    }

    public void setInsuredPerson(PolicyHolder insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }

    public void setAppliedPolicy(Policy appliedPolicy) {
        this.appliedPolicy = appliedPolicy;
    }

    public void setClaimDate(LocalDateTime claimDate) {
        this.claimDate = claimDate;
    }

    public void setExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
return "Claim{" +
                "id='" + id + '\'' +
                ", insuredPerson={" + insuredPerson.getCustomerID() + " " + insuredPerson.getCardNum() +
                "}, requestAmount=" + requestAmount +
                ", appliedPolicy=" + appliedPolicy +
                ", claimDate=" + claimDate.format(formatter) +
                ", examDate=" + examDate.format(formatter) +
                ", claimAmount=" + claimAmount +
                ", provider=" + provider.getProviderID() +
                ", documents=" + documents +
                ", claimStatus=" + claimStatus +
                '}';
    }
}