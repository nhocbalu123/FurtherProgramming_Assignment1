package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
public class Dependent extends Customer{
    // Constructor
    public Dependent() {
        super();
    }
    public Dependent(String id, String fullName) {
        super(id, fullName);
    }

    @Override
    public String toString() {
        return "Dependent{" +
                "ID = '" + id + '\'' +
                ", Full name = '" + fullName + '\'' +
                ", Insurance card = " + card.getCardNumber() +
                ", Number of claims = " + claims.size() +
                '}';
    }
}
