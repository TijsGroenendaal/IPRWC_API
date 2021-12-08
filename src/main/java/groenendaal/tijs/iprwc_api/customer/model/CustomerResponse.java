package groenendaal.tijs.iprwc_api.customer.model;

import lombok.Value;

import java.util.UUID;

@Value
public class CustomerResponse {
    UUID id;
    String email;

    public CustomerResponse(CustomerEntity customerEntity) {
        this.id = customerEntity.getId();
        this.email = customerEntity.getEmail();
    }
}
