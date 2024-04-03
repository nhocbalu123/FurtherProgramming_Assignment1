package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    //Create an instance of ClaimProcessManagerImpl
    private static ClaimProcessManagerImp manager = new ClaimProcessManagerImp();
    private static CustomerProcessManagerImp customerManager = new CustomerProcessManagerImp();
    private static void loadCustomers() {
        try (Scanner scanner = new Scanner(new File("src\\customers.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                if (type.equals("PolicyHolder")) {
                    PolicyHolder policyHolder = new PolicyHolder(id, name);
                    customerManager.addCustomer(policyHolder);
                } else if (type.equals("Dependent")) {
                    Dependent dependent = new Dependent(id, name);
                    customerManager.addCustomer(dependent);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*
        //Create an instance of Claim
        Claim newClaim = new Claim("f-1234567890");
        manager.add(newClaim);
        Claim newClaim2 = new Claim("f-111111111");
        manager.add(newClaim2);
        System.out.println(newClaim.getId());

        //Get all claims
        List<Claim> allClaims = manager.getAll();

        //Print all claims
        for (Claim claim : allClaims) {
            System.out.println(claim);
        }
        */


        //Get all customers
        loadCustomers();
        List<Customer> customers = customerManager.getAllCustomers();
        //Print all customers
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
