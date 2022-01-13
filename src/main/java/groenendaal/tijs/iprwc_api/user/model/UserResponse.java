package groenendaal.tijs.iprwc_api.user.model;

import lombok.Value;

import java.util.UUID;

@Value
public class UserResponse {
    UUID id;
    String username;
    Role role;

    public UserResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.role = userEntity.getRole();
    }
}
