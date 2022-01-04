package groenendaal.tijs.iprwc_api.order;

import groenendaal.tijs.iprwc_api.order.model.OrderEntity;
import groenendaal.tijs.iprwc_api.order.model.OrderResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping()
    public Iterable<OrderResponse> getOrders() {
        return orderService.getOrders();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping()
    public OrderResponse createOrder(
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
