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
    private ClaimStatus claimStatus;
    enum ClaimStatus {
        NEW,
        PROCESSING,
        DONE
    }
    private Bank bankInfo;


    //Constructor
    public Claim(String id, Date claimDate, Customer insuredPerson, String cardNumber, Date examDate,
                 List<String> documents, double claimAmount, ClaimStatus claimStatus, Bank bankInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        this.bankInfo = bankInfo;
        this.claimStatus = claimStatus;
        this.documents = documents;
    }
    public Claim() {}

    //Getter
    public String getId() {
        return this.id;
    }

    //Setter
    public void setId(String id) {
        this.id = id;
    }
    public void setInsuredPerson(Customer customer) {
        this.insuredPerson = customer;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "ID = " + id +
                ", Claim Date = " + claimDate +
                ", Insured Person = '" + insuredPerson.getFullName() + '\'' +
                ", Card Number = " + cardNumber +
                ", Exam Date = " + examDate +
                ", Claim Amount = " + claimAmount +
                ", Claim Status = " + claimStatus +
                ", Bank Info = " + bankInfo.getBankName() +
                ", Number of Documents = " + documents.size() +
                '}';
    }

}
