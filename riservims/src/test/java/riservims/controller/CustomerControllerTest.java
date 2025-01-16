package riservims.controller;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import riservims.model.Customer;
import riservims.service.CustomerService;

import java.util.List;
import java.util.Arrays;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCustomer_ShouldReturnCreatedCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(customer);
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
    }
}