package groenendaal.tijs.iprwc_api.customer.model;

import lombok.Value;

import java.util.UUID;

@Value
public class UserResponse {
    UUID id;
    String email;

    public UserResponse(UserEntity customerEntity) {
        this.id = customerEntity.getId();
        this.email = customerEntity.getUsername();
    }
}
