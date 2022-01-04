package groenendaal.tijs.iprwc_api.order;

import groenendaal.tijs.iprwc_api.customer.UserRepository;
import groenendaal.tijs.iprwc_api.customer.model.UserEntity;
import groenendaal.tijs.iprwc_api.exception.EntityNotFoundException;
import groenendaal.tijs.iprwc_api.exception.InvalidJwtException;
import groenendaal.tijs.iprwc_api.helper.RelationHelper;
import groenendaal.tijs.iprwc_api.order.model.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Iterable<OrderEntity> getOrders() {
        final UserEntity userEntity = userRepository.findById(RelationHelper.getUserId())
                .orElseThrow(() -> new InvalidJwtException());

        return orderRepository.getAllByUser(userEntity);
    }

    public OrderEntity createOrder(OrderEntity orderEntity) {
        final UserEntity userEntity = userRepository.findById(RelationHelper.getUserId())
                .orElseThrow(() -> new InvalidJwtException());

        orderEntity.setUser(userEntity);

        return orderRepository.save(orderEntity);
    }

    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }
}
