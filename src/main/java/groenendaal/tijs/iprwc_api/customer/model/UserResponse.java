package groenendaal.tijs.iprwc_api.customer.model;

import lombok.Value;

import java.util.UUID;

@Value
public class UserResponse {
    UUID id;
    String username;

    public UserResponse(UserEntity customerEntity) {
        this.id = customerEntity.getId();
        this.username = customerEntity.getUsername();
    }
}
