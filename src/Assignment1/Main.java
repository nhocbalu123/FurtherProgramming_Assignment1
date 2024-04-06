package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.loadCustomers();
        dataManager.loadInsuranceCards();
        dataManager.loadClaims();

        //Get the list of customers from dataManager
        List<Customer> customers = dataManager.getCustomers();

        // Create a new Claim
        Claim claim = new Claim();
        claim.setId("f-1234567890");


        // Add the claim to a specific customer
        for (Customer customer : customers) {
            if (customer.getId().equals("c-1234567")) {
                customer.add(claim);
                claim.setInsuredPerson(customer);
                break;
            }
        }


        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}




