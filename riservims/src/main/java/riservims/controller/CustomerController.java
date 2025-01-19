package riservims.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riservims.dto.CustomerResponse;
import riservims.model.Customer;
import riservims.service.CustomerService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        log.info("Create customer: {}", customer);

        CustomerResponse customerResponse = new CustomerResponse();
        Customer savedCustomer = customerService.saveCustomer(customer);
        if(savedCustomer == null) {
            log.info("null");
            customerResponse.setId("-1");
            customerResponse.setMessage("Customer not saved, customer exists!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customerResponse);
        }
        else {
            log.info(savedCustomer.getId());
            customerResponse.setId(savedCustomer.getId());
            customerResponse.setMessage("Customer successfully created!");
            return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
}