package groenendaal.tijs.iprwc_api.order;

import groenendaal.tijs.iprwc_api.order.model.OrderEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @GetMapping()
    public Iterable<OrderEntity> getOrders() {
        return orderService.getOrders();
    }

    @PostMapping()
    public OrderEntity createOrder(
            @RequestBody OrderEntity orderEntity
    ) {
        return orderService.createOrder(orderEntity);
    }

//    @DeleteMapping("/{orderId}")
    public void deleteOrder(
            @PathVariable UUID orderId
    ) {
        orderService.deleteOrder(orderId);
    }

}
