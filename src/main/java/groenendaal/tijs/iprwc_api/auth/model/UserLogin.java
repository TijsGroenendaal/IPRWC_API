package groenendaal.tijs.iprwc_api.auth.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLogin {

    @NotEmpty
    String password;
    @NotEmpty
    String username;
}
