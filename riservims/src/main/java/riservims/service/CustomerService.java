package riservims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riservims.model.Customer;
import riservims.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {

        if(customerRepository.findByEmail(customer.getEmail()).isEmpty()) {
            return customerRepository.save(customer);
        }
        return null;
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}