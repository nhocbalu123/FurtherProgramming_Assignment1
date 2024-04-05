package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
public class Dependent extends Customer{
    private String id;
    private String fullName;
    private InsuranceCard card;

    // Constructor
    public Dependent(String id1, String fullName1, InsuranceCard card1) {
        this.id = id1;
        this.fullName = fullName1;
        this.card = card1;
    }

    @Override
    public String toString() {
        return "Policy Holder{" +
                "ID ='" + id + '\'' +
                ", Full name ='" + fullName + '\'' +
                ", Insurance card =" + card +
                ", Number of claims =" + claims.size() +
                '}';
    }
}
