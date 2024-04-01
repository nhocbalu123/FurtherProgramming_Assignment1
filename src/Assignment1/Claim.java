package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.Date;
import java.util.List;

public class Claim {
    private String id;
    private Date claimDate;
    private Customer insuredPerson;
    private String cardNumber;
    private Date examDate;
    private List<String> documents;
    private double claimAmount;
    private String status;
    private String receiverBankingInfo;

    //Constructor
    public Claim(String s) {
        this.id = s;
    }

    //Getter
    public String getId() {
        return this.id;
    }

    //toString
    public String toString() {
        return id;
    }
}
