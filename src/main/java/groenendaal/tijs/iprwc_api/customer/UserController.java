package groenendaal.tijs.iprwc_api.customer;

import groenendaal.tijs.iprwc_api.customer.model.UserEntity;
import groenendaal.tijs.iprwc_api.customer.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public Iterable<UserResponse> getAllCustomer() {
        return userService.getAllCustomer();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PatchMapping("/{userId}")
    public UserResponse updateCustomer(
            @RequestBody UserEntity userEntity,
            @PathVariable UUID userId
    ) {
        return userService.updateCustomer(userEntity, userId);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping("/{userId}")
    public void deleteCustomer(
            @PathVariable UUID userId
    ) {
        userService.deleteCustomer(userId);
    }
}
