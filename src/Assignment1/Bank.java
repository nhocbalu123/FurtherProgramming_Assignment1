package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
public class Bank {
    private String bankAccountNumber;
    private String receiverName;
    private String bankName;

    //Constructor
    public Bank(String bankAccountNumber, String bankName, String receiverName) {
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.receiverName = receiverName;
    }
}
