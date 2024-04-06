package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.text.SimpleDateFormat;
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
    public Date getClaimDate() {
        return this.claimDate;
    }


    //Setter
    public void setId(String id) {
        this.id = id;
    }
    public void setInsuredPerson(Customer customer) {
        this.insuredPerson = customer;
    }
    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }
    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }
    public void setBankInfo(Bank bankInfo) {
        this.bankInfo = bankInfo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Claim{" +
                "ID = '" + id + '\'' +
                ", Claim Date = " + sdf.format(claimDate) +
                ", Insured Person = '" + insuredPerson.getFullName() + '\'' +
                ", Card Number = " + cardNumber +
                ", Exam Date = " + sdf.format(examDate) +
                ", Claim Amount = " + claimAmount +
                ", Claim Status = " + claimStatus +
                ", Bank Info = " + bankInfo.getBankName() +
                ", Number of Documents = " + documents.size() +
                '}';
    }

}
