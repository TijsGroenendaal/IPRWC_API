package groenendaal.tijs.iprwc_api.customer;

import groenendaal.tijs.iprwc_api.customer.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
