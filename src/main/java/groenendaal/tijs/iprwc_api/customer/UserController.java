package groenendaal.tijs.iprwc_api.customer;

import groenendaal.tijs.iprwc_api.customer.model.UserEntity;
import groenendaal.tijs.iprwc_api.customer.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/customer")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping()
    public Iterable<UserResponse> getAllCustomer() {
        return userService.getAllCustomer();
    }

    @GetMapping("/{userId}")
    public UserResponse getCustomer(
            @PathVariable UUID userId
    ) {
        return userService.getCustomer(userId);
    }

//    @PostMapping()
    public UserResponse createCustomer(
            @RequestBody UserEntity userEntity
    ) {
        return userService.createCustomer(userEntity);
    }

    @PatchMapping("/{userId}")
    public UserResponse updateCustomer(
            @RequestBody UserEntity userEntity,
            @PathVariable UUID userId
    ) {
        return userService.updateCustomer(userEntity, userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteCustomer(
            @PathVariable UUID userId
    ) {
        userService.deleteCustomer(userId);
    }
}
