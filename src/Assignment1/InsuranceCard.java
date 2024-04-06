package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.Date;

public class InsuranceCard {
    private String cardNumber;
    private Customer cardHolder;
    private String policyOwner;
    private Date expirationDate;

    //Constructor
    public InsuranceCard(String cardNum, Customer cardHolder, String policyOwner, Date expirationDate) {
        this.cardNumber = cardNum;
        this.cardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
    }

    //Getter
    public String getCardNumber() {
        return this.cardNumber;
    }
}
