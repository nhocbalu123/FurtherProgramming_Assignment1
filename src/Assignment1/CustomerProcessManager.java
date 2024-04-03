package Assignment1;

import java.util.List;

public interface CustomerProcessManager {
    void addCustomer(Customer customer);
    Customer getCustomerById(String id);
    void updateCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    List<Customer> getAllCustomers();
}