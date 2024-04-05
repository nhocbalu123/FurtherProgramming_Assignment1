package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Customer> customers = new ArrayList<Customer>();

    //Load file
    private static void loadCustomers() {
        try (Scanner scanner = new Scanner(new File("src\\customers.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                String cardNumber = parts[3];
                InsuranceCard card = new InsuranceCard(cardNumber);
                if (type.equals("Policy Holder")) {
                    PolicyHolder policyHolder = new PolicyHolder(id, name, card);
                    customers.add(policyHolder);
                } else if (type.equals("Dependent")) {
                    Dependent dependent = new Dependent(id, name, card);
                    customers.add(dependent);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadCustomers();

        Claim newClaim = new Claim("f-1234567890");
        Claim newClaim2 = new Claim("f-111111111");
        System.out.println(newClaim.getId());

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}




