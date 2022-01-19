package groenendaal.tijs.iprwc_api.order;

import groenendaal.tijs.iprwc_api.order.model.OrderEntity;
import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Iterable<OrderEntity> getAllByUser(UserEntity userEntity);
}
