package groenendaal.tijs.iprwc_api.order;

import groenendaal.tijs.iprwc_api.cartItem.CartItemRepository;
import groenendaal.tijs.iprwc_api.cartItem.CartItemService;
import groenendaal.tijs.iprwc_api.exception.InvalidJwtException;
import groenendaal.tijs.iprwc_api.helper.RelationHelper;
import groenendaal.tijs.iprwc_api.order.model.OrderEntity;
import groenendaal.tijs.iprwc_api.order.model.OrderLineEntity;
import groenendaal.tijs.iprwc_api.order.model.OrderResponse;
import groenendaal.tijs.iprwc_api.user.UserRepository;
import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository,
            CartItemRepository cartItemRepository,
            CartItemService cartItemService
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartItemService = cartItemService;
    }

    public Iterable<OrderResponse> getOrders() {
        final UserEntity userEntity = userRepository.findById(RelationHelper.getUserId())
                .orElseThrow(InvalidJwtException::new);

        final Set<OrderResponse> orderResponseSet = new HashSet<>();
        orderRepository.getAllByUser(userEntity).forEach(orderEntity ->
                orderResponseSet.add(new OrderResponse(orderEntity)));
        return orderResponseSet;
    }

    public OrderResponse createOrder(OrderEntity orderEntity) {
        final UserEntity userEntity = userRepository.findById(RelationHelper.getUserId())
                .orElseThrow(InvalidJwtException::new);

        orderEntity.setUser(userEntity);

        Set<OrderLineEntity> orderLineEntitySet = new HashSet<>();
        cartItemRepository.findByUserEntity(userEntity).forEach(cartItemEntity ->
                orderLineEntitySet.add(new OrderLineEntity(cartItemEntity.getProductEntity(), cartItemEntity, orderEntity)));
        orderEntity.setOrderLines(orderLineEntitySet);

        cartItemService.clearCart();

        return new OrderResponse(orderRepository.save(orderEntity));
    }

    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }
}
