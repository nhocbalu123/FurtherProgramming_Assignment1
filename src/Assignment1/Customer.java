package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.List;

public abstract class Customer {
    private String id;
    private String fullName;
    private InsuranceCard card;
    private List<Claim> claims;

    //Getter
    public String getId() {
        return this.id;
    }
    public abstract String toString();
}
