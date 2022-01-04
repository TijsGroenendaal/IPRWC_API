package groenendaal.tijs.iprwc_api.user.model;

import lombok.Value;

import java.util.UUID;

@Value
public class UserResponse {
    UUID id;
    String username;

    public UserResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
    }
}
