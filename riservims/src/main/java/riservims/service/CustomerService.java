package riservims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riservims.model.Customer;
import riservims.repository.CustomerRepository;
import riservims.repository.CustomerTypeRepository;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerTypeRepository customerTypeRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerTypeRepository customerTypeRepository) {
        this.customerRepository = customerRepository;
        this.customerTypeRepository = customerTypeRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}