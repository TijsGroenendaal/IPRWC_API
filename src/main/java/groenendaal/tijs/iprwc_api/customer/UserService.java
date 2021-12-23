package groenendaal.tijs.iprwc_api.customer;

import groenendaal.tijs.iprwc_api.customer.model.Role;
import groenendaal.tijs.iprwc_api.customer.model.UserEntity;
import groenendaal.tijs.iprwc_api.customer.model.UserResponse;
import groenendaal.tijs.iprwc_api.exception.EntityNotFoundException;
import groenendaal.tijs.iprwc_api.exception.NameAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Value("${admin.hashed-password}")
    private String adminPassword;

    @Value("${admin.username}")
    private String adminUsername;

    @Autowired
    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public Iterable<UserResponse> getAllCustomer() {
        ArrayList<UserResponse> userResponse = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> {
            userResponse.add(new UserResponse(userEntity));
        });
        return userResponse;
    }

    public UserResponse getCustomer(
            UUID customerId
    ) {
        return new UserResponse(userRepository.getById(customerId));
    }

    public UserResponse createCustomer(
            UserEntity customerEntity
    ) {
        if (userRepository.existsByUsername(customerEntity.getUsername())) {
            throw new NameAlreadyInUseException(customerEntity.getUsername());
        } else {
            return new UserResponse(userRepository.save(customerEntity));
        }
    }

    public UserResponse updateCustomer(
            UserEntity customerEntity, UUID customerId
    ) {
        userRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException(UserEntity.class));
        return new UserResponse(userRepository.save(customerEntity));
    }

    public void deleteCustomer(
            UUID customerId
    ) {
        userRepository.deleteById(customerId);
    }

    public UserEntity findOneByUsername(String username) {
        if (username.equals(adminUsername)) {
            UserEntity user = new UserEntity();
            user.setPassword(new String(Base64.getDecoder().decode(adminPassword)));
            user.setRole(Role.ADMIN);
            return user;
        }
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(UserEntity.class));
    }

}
