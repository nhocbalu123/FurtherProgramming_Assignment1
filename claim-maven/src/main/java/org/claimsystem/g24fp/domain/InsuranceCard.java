package org.claimsystem.g24fp.domain;



import org.claimsystem.g24fp.domain.user.PolicyHolder;

import java.time.LocalDate;

public class InsuranceCard {
    private String cardNumber;
    private PolicyHolder cardHolder;
    private LocalDate expirationDate;

    public InsuranceCard(String cardNum, PolicyHolder cardHolder, LocalDate expirationDate) {
        this.cardNumber = cardNum;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
    }

    //Getter
    public String getCardNumber() {
        return this.cardNumber;
    }

    public PolicyHolder getCardHolder() {
        return cardHolder;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder=" + cardHolder + '\'' +
                ", expirationDate=" + expirationDate;
    }
}
