package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
public class Bank {
    private String receiverName;
    private String bankAccountNumber;
    private String bankName;

    //Constructor
    public Bank(String bankAccountNumber, String receiverName, String bankName) {
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.receiverName = receiverName;
    }
    //Getter
    public String getBankName() {
        return this.bankName;
    }
}
