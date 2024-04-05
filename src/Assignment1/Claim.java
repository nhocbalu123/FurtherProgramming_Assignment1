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
    enum ClaimStatus {
        NEW,
        PROCESSING,
        DONE
    }
    private Bank bankInfo;


    //Constructor
    public Claim(String id) {
        this.id = id;
    }
    public Claim(String id, Date claimDate, Customer insuredPerson, String cardNumber,
                 Date examDate, double claimAmount, ClaimStatus claimStatus, Bank bankInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        //this.ClaimStatus = claimStatus;
        this.bankInfo = bankInfo;
    }

    //Getter
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("Id: %s", id);
    }
}
