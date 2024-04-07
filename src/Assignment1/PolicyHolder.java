package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.ArrayList;
import java.util.List;


public class PolicyHolder extends Customer{
    private List<Dependent> dependents;

    // Constructor
    public PolicyHolder() {
        super();
        this.dependents = new ArrayList<>();
    }
    public PolicyHolder(String id, String fullName, InsuranceCard insuranceCard) {
        super(id, fullName, insuranceCard);
        this.dependents = new ArrayList<>();
    }

    public PolicyHolder(String id, String fullName) {
        super(id, fullName);
        this.dependents = new ArrayList<>();
    }

    //Getter
    public List<Dependent> getDependents() {
        return this.dependents;
    }
    @Override
    public String toString() {
        return "Policy Holder{" +
                "ID = '" + id + '\'' +
                ", Full name = '" + fullName + '\'' +
                ", Insurance card = " + card.getCardNumber() +
                ", Number of dependents = " + dependents.size() +
                ", Number of claims = " + claims.size() +
                '}';
    }


}
