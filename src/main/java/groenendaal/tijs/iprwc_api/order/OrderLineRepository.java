package groenendaal.tijs.iprwc_api.order;

import groenendaal.tijs.iprwc_api.order.model.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, UUID> {
}
