package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
public class Dependent extends Customer{
    private String id;
    private String fullName;
    private InsuranceCard card;

    // Constructor
    public Dependent(String id1, String fullName1) {
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
