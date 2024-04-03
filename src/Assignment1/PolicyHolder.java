package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.List;


public class PolicyHolder extends Customer{
    private List<Customer> dependents;
    private String id;
    private String fullName;
    private InsuranceCard card;

    // Constructor
    public PolicyHolder(String id1, String fullName1) {
        this.id = id1;
        this.fullName = fullName1;
    }

    @Override
    public String toString() {
        // String.format() is similar to printf, but it return instead of display
        return String.format("Id: %s, Name: %s",
                id, fullName);
    }
}
