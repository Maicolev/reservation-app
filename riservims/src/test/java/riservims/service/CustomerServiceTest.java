package riservims.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import riservims.model.Customer;
import riservims.repository.CustomerRepository;
import riservims.repository.CustomerTypeRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerTypeRepository customerTypeRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId("1");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
    }

    @Test
    void testSaveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("1", savedCustomer.getId());
        assertEquals("John", savedCustomer.getFirstName());
        assertEquals("Doe", savedCustomer.getLastName());
        assertEquals("john.doe@example.com", savedCustomer.getEmail());
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        List<Customer> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
    }
}