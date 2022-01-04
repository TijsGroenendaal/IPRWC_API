package groenendaal.tijs.iprwc_api.auth.model;

import groenendaal.tijs.iprwc_api.user.model.UserResponse;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserLoginResult {

    @NonNull
    private String token;
    @NonNull
    private UserResponse user;
}
