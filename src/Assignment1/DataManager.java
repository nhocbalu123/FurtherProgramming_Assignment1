package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataManager {
    private List<Customer> customers;

    //Constructor
    public DataManager() {
        this.customers = new ArrayList<>();
    }

    //Getter
    public List<Customer> getCustomers() {
        return this.customers;
    }

    public void loadCustomers() {
        try (Scanner scanner = new Scanner(new File("src\\customers.csv"))) {
            //Load all customers
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String type = parts[0];
                String id = parts[1];
                String name = parts[2];

                if (type.equals("PolicyHolder")) {
                    PolicyHolder policyHolder = new PolicyHolder(id, name);
                    customers.add(policyHolder);
                } else if (type.equals("Dependent")) {
                    Dependent dependent = new Dependent(id, name);
                    customers.add(dependent);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Check if a dependent belong to a policyholder
        try (Scanner scanner = new Scanner(new File("src\\customers.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String type = parts[0];
                String id = parts[1];
                String[] dependentIds = parts.length > 3 ? parts[3].split(";") : null;

                if (type.equals("PolicyHolder") && dependentIds != null) {
                    PolicyHolder policyHolder = (PolicyHolder) getCustomerById(id);
                    for (String dependentId : dependentIds) {
                        Dependent dependent = (Dependent) getCustomerById(dependentId);
                        if (dependent != null) {
                            assert policyHolder != null;
                            policyHolder.getDependents().add(dependent);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadInsuranceCards() {
        try (Scanner scanner = new Scanner(new File("src\\insurancecards.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String cardNumber = parts[0];
                String customerId = parts[1];
                String policyOwner = parts[2];
                Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[3]);

                // Find the customer to which this card belongs
                for (Customer customer : customers) {
                    if (customer.getId().equals(customerId)) {
                        InsuranceCard card = new InsuranceCard(cardNumber, customer, policyOwner, expirationDate);
                        customer.setCard(card);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void loadClaims() {
        try (Scanner scanner = new Scanner(new File("src\\claims.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String id = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String customerId = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                List<String> documents = Arrays.asList(parts[5].split(";"));
                double claimAmount = Double.parseDouble(parts[6]);
                Claim.ClaimStatus claimStatus = Claim.ClaimStatus.valueOf(parts[7]);
                String bankAccountNumber = parts[8];
                String receiverName = parts[9];
                String bankName = parts[10];
                Bank bankInfo = new Bank(bankAccountNumber, receiverName, bankName);

                // Find the customer to which this claim belongs
                for (Customer customer : customers) {
                    if (customer.getId().equals(customerId)) {
                        Claim claim = new Claim(id, claimDate, customer, cardNumber, examDate,
                                documents, claimAmount, claimStatus, bankInfo);
                        customer.add(claim);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
}
