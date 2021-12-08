package groenendaal.tijs.iprwc_api.customer;

import groenendaal.tijs.iprwc_api.customer.model.CustomerEntity;
import groenendaal.tijs.iprwc_api.customer.model.CustomerResponse;
import groenendaal.tijs.iprwc_api.exception.EntityNotFoundException;
import groenendaal.tijs.iprwc_api.exception.NameAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(
            CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public Iterable<CustomerResponse> getAllCustomer() {
        ArrayList<CustomerResponse> customerResponses = new ArrayList<>();
        customerRepository.findAll().forEach(customerEntity -> {
            customerResponses.add(new CustomerResponse(customerEntity));
        });
        return customerResponses;
    }

    public CustomerResponse getCustomer(
            UUID customerId
    ) {
        return new CustomerResponse(customerRepository.getById(customerId));
    }

    public CustomerResponse createCustomer(
            CustomerEntity customerEntity
    ) {
        if (customerRepository.existsByEmail(customerEntity.getEmail())) {
            throw new NameAlreadyInUseException(customerEntity.getEmail());
        } else {
            return new CustomerResponse(customerRepository.save(customerEntity));
        }
    }

    public CustomerResponse updateCustomer(
            CustomerEntity customerEntity, UUID customerId
    ) {
        customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(CustomerEntity.class));
        return new CustomerResponse(customerRepository.save(customerEntity));
    }

    public void deleteCustomer(
            UUID customerId
    ) {
        customerRepository.deleteById(customerId);
    }
}
