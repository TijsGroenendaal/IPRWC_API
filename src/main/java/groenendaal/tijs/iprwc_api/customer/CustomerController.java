package groenendaal.tijs.iprwc_api.customer;

import groenendaal.tijs.iprwc_api.customer.model.CustomerEntity;
import groenendaal.tijs.iprwc_api.customer.model.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(
            CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @GetMapping()
    public Iterable<CustomerResponse> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomer(
            @PathVariable UUID customerId
    ) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping()
    public CustomerResponse createCustomer(
            @RequestBody CustomerEntity customerEntity
    ) {
        return customerService.createCustomer(customerEntity);
    }

    @PatchMapping("/{customerId}")
    public CustomerResponse updateCustomer(
            @RequestBody CustomerEntity customerEntity,
            @PathVariable UUID customerId
    ) {
        return customerService.updateCustomer(customerEntity, customerId);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(
            @PathVariable UUID customerId
    ) {
        customerService.deleteCustomer(customerId);
    }
}
