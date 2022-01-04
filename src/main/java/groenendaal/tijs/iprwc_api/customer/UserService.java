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

    public Iterable<UserResponse> getAllUsers() {
        ArrayList<UserResponse> userResponse = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> {
            userResponse.add(new UserResponse(userEntity));
        });
        return userResponse;
    }

    public UserResponse getUser(
            UUID userId
    ) {
        return new UserResponse(userRepository.getById(userId));
    }

    public UserResponse createUser(
            UserEntity userEntity
    ) {
        if (userRepository.existsByUsername(userEntity.getUsername())) {
            throw new NameAlreadyInUseException(userEntity.getUsername());
        } else {
            userEntity.setRole(Role.CUSTOMER);

            return new UserResponse(userRepository.save(userEntity));
        }
    }

    public UserResponse updateUser(
            UserEntity userEntity, UUID userId
    ) {
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(UserEntity.class));
        return new UserResponse(userRepository.save(userEntity));
    }

    public void deleteUser(
            UUID userId
    ) {
        userRepository.deleteById(userId);
    }

    public UserEntity findOneByUsername(String username) {
        if (username.equals(adminUsername)) {
            UserEntity user = new UserEntity();
            user.setUsername(adminUsername);
            user.setPassword(new String(Base64.getDecoder().decode(adminPassword)));
            user.setRole(Role.ADMIN);
            return user;
        }
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(UserEntity.class));
    }

}
