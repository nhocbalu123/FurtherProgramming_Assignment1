package Assignment1;
/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UI {
    private DataManager dataManager;
    private Scanner scanner;

    public UI(DataManager dataManager) {
        this.dataManager = dataManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayCustomers() {
        List<Customer> customers = dataManager.getCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.\n");
            return;
        }

        //Sort list of customers by ID
        customers.sort(new Comparator<>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                return c1.getId().compareTo(c2.getId()); //Ascending order
            }
        });

        //Print all customers
        System.out.println("List of customers, sorted by ID in ascending order:\n");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println();
    }



    public void displayClaims() {
        System.out.println("Please enter the ID of the customer:");
        String customerId = scanner.nextLine();

        //Get list of claims of a customer
        List<Claim> claims = null;
        for (Customer customer : dataManager.getCustomers()) {
            if (customer.getId().equals(customerId)) {
                claims = customer.getAll();
                break;
            }
        }

        if (claims == null) {
            System.out.println("No customer found with the given ID.\n");
            return;
        }

        //Sort list of claims by date
        claims.sort((c1, c2) -> {
            return c2.getClaimDate().compareTo(c1.getClaimDate()); //Descending order
        });

        //Print all claims of a customer
        System.out.println("List of claims, sorted by claim date in descending order:\n");
        for (Claim claim : claims) {
            System.out.println(claim);
        }
        System.out.println();

        //Ask the user if they want to save the list to a file
        System.out.println("Do you want to save this list to a file? (yes/no)");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Please enter the filename:");
            String filename = scanner.nextLine();

            try (PrintWriter writer = new PrintWriter(filename)) {
                for (Claim claim : claims) {
                    writer.println(claim);
                }
                System.out.println("Claims saved to " + filename);
            } catch (IOException e) {
                System.out.println("An error occurred while saving the file.");
            }
        }
    }

    public void addClaim() {
        System.out.println("Please enter the ID of the customer:");
        String customerId = scanner.nextLine();

        Customer customer = dataManager.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("No customer found with the given ID.\n");
            return;
        }

        //Enter claimID
        String claimId = null;
        while (claimId == null) {
            System.out.println("Please enter the ID of the claim (f followed by '-' and 10 digits):");
            claimId = scanner.nextLine();
            if (!claimId.matches("f-\\d{10}")) {
                System.out.println("Invalid ID format. Please enter a valid ID.\n");
                claimId = null;
            }
        }

        Date claimDate = null;
        while (claimDate == null) {
            System.out.println("Please enter the claim date (dd/MM/yyyy):");
            String claimDateString = scanner.nextLine();
            try {
                claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(claimDateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a valid date.\n");
            }
        }

        String cardNumber = null;
        while (cardNumber == null) {
            System.out.println("Please enter the card number (10 digits):");
            cardNumber = scanner.nextLine();
            if (!cardNumber.matches("\\d{10}")) {
                System.out.println("Invalid card number. Please enter a valid card number.\n");
                cardNumber = null;
            }
        }

        Date examDate = null;
        while (examDate == null) {
            System.out.println("Please enter the exam date (dd/MM/yyyy):");
            String examDateString = scanner.nextLine();
            try {
                examDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a valid date.\n");
            }
        }

        System.out.println("Please enter the claim amount:");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine();

        Claim.ClaimStatus claimStatus = null;
        while (claimStatus == null) {
            System.out.println("Please enter the claim status (NEW, PROCESSING, DONE):");
            String claimStatusString = scanner.nextLine();
            try {
                claimStatus = Claim.ClaimStatus.valueOf(claimStatusString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid claim status. Please enter a valid status.\n");
            }
        }

        System.out.println("Bank info:");
        String receiverName = customer.getFullName();

        System.out.println("Please enter the bank account number:");
        String bankAccountNumber = scanner.nextLine();

        System.out.println("Please enter the bank name:");
        String bankName = scanner.nextLine();

        Bank bankInfo = new Bank(bankAccountNumber, receiverName, bankName);

        System.out.println("Please enter the number of documents:");
        int numDocuments = scanner.nextInt();
        scanner.nextLine();

        List<String> documents = new ArrayList<>();
        for (int i = 0; i < numDocuments; i++) {
            System.out.println("Please enter document " + (i+1) + " in the format 'ClaimId_CardNumber_DocumentName.pdf':");
            String document = scanner.nextLine();
            documents.add(document);
        }


        Claim claim = new Claim();
        claim.setId(claimId);
        claim.setClaimDate(claimDate);
        claim.setInsuredPerson(customer);
        claim.setCardNumber(cardNumber);
        claim.setExamDate(examDate);
        claim.setDocuments(documents);
        claim.setClaimAmount(claimAmount);
        claim.setClaimStatus(claimStatus);
        claim.setBankInfo(bankInfo);

        customer.add(claim);
        System.out.println("Claim added successfully.\n");
    }

    public void updateClaim() {
        System.out.println("Please enter the ID of the customer:");
        String customerId = scanner.nextLine();

        Customer customer = dataManager.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("No customer found with the given ID.\n");
            return;
        }

        System.out.println("Please enter the ID of the claim:");
        String claimId = scanner.nextLine();

        Claim claim = customer.getOne(claimId);
        if (claim == null) {
            System.out.println("No claim found with the given ID.\n");
            return;
        }

        //Enter claim date
        Date claimDate = null;
        while (claimDate == null) {
            System.out.println("Please enter the new claim date (dd/MM/yyyy):");
            String claimDateString = scanner.nextLine();
            try {
                claimDate = new SimpleDateFormat("dd/MM/yyyy").parse(claimDateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a valid date.\n");
            }
        }

        String cardNumber = null;
        while (cardNumber == null) {
            System.out.println("Please enter the new card number (10 digits):");
            cardNumber = scanner.nextLine();
            if (!cardNumber.matches("\\d{10}")) {
                System.out.println("Invalid card number. Please enter a valid card number.\n");
                cardNumber = null;
            }
        }

        //Enter exam date
        Date examDate = null;
        while (examDate == null) {
            System.out.println("Please enter the new exam date (dd/MM/yyyy):");
            String examDateString = scanner.nextLine();
            try {
                examDate = new SimpleDateFormat("dd/MM/yyyy").parse(examDateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a valid date.\n");
            }
        }

        System.out.println("Please enter the new claim amount:");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine();

        Claim.ClaimStatus claimStatus = null;
        while (claimStatus == null) {
            System.out.println("Please enter the new claim status (NEW, PROCESSING, DONE):");
            String claimStatusString = scanner.nextLine();
            try {
                claimStatus = Claim.ClaimStatus.valueOf(claimStatusString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid claim status. Please enter a valid status.\n");
            }
        }

        System.out.println("New bank info:");
        String receiverName = customer.getFullName();

        System.out.println("Please enter the new bank account number:");
        String bankAccountNumber = scanner.nextLine();

        System.out.println("Please enter the new bank name:");
        String bankName = scanner.nextLine();

        Bank bankInfo = new Bank(bankAccountNumber, receiverName, bankName);

        System.out.println("Please enter the new number of documents:");
        int numDocuments = scanner.nextInt();
        scanner.nextLine();

        List<String> documents = new ArrayList<>();
        for (int i = 0; i < numDocuments; i++) {
            System.out.println("Please enter document " + (i+1) + " in the format 'ClaimId_CardNumber_DocumentName.pdf':");
            String document = scanner.nextLine();
            documents.add(document);
        }

        claim.setClaimDate(claimDate);
        claim.setCardNumber(cardNumber);
        claim.setExamDate(examDate);
        claim.setDocuments(documents);
        claim.setClaimAmount(claimAmount);
        claim.setClaimStatus(claimStatus);
        claim.setBankInfo(bankInfo);

        customer.update(claim);
        System.out.println("Claim updated successfully.\n");
    }


    public void deleteClaim() {
        System.out.println("Please enter the ID of the customer:");
        String customerId = scanner.nextLine();
        System.out.println("Please enter the ID of the claim:");
        String claimId = scanner.nextLine();

        Customer customer = dataManager.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("No customer found with the given ID.\n");
            return;
        }

        Claim claim = customer.getOne(claimId);
        if (claim == null) {
            System.out.println("No claim found with the given ID.\n");
            return;
        }

        customer.delete(claim);
        System.out.println("Claim deleted successfully.\n");
    }

    public void start() {
        System.out.println("\nWELCOME TO INSURANCE CLAIMS MANAGEMENT SYSTEM! - by BALU\n");
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Display all customers");
            System.out.println("2. Display all claims of a customer");
            System.out.println("3. Add a claim to a customer");
            System.out.println("4. Update a claim of a customer");
            System.out.println("5. Delete a claim of a customer");
            System.out.println("6. Exit");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    //Display all customers
                    displayCustomers();
                break;
                case "2":
                    //Display all claims
                    displayClaims();
                break;
                case "3":
                    //Add a claim
                    addClaim();
                    break;
                case "4":
                    //Update a claim
                    updateClaim();
                    break;
                case "5":
                    //Delete a claim
                    deleteClaim();
                    break;
                case "6":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
