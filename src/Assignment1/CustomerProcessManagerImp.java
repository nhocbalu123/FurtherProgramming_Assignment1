package Assignment1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <Nguyen Quy Minh Thang - s3978302>
 */

public class CustomerProcessManagerImp implements CustomerProcessManager {
    private List<Customer> customers = new ArrayList<>();

    // Add customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Get one customer
    public Customer getCustomerById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    // Update customer
    public void updateCustomer(Customer customer) {

    }

    // Delete customer
    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

    // Get the customer list
    public List<Customer> getAllCustomers() {
        return customers;
    }
}


