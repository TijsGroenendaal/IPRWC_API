package groenendaal.tijs.iprwc_api.user;

import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import groenendaal.tijs.iprwc_api.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public Iterable<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public UserResponse getUser(
            @PathVariable UUID userId
    ) {
        return userService.getUser(userId);
    }

//    @PostMapping()
    public UserResponse createUser(
            @RequestBody UserEntity userEntity
    ) {
        return userService.createUser(userEntity);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PatchMapping("/{userId}")
    public UserResponse updateUser(
            @RequestBody UserEntity userEntity,
            @PathVariable UUID userId
    ) {
        return userService.updateUser(userEntity, userId);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping("/{userId}")
    public void deleteUser(
            @PathVariable UUID userId
    ) {
        userService.deleteUser(userId);
    }
}
