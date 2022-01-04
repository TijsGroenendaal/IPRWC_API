package groenendaal.tijs.iprwc_api.order.model;

import lombok.Value;

import java.util.HashSet;
import java.util.Set;

@Value
public class OrderResponse {

    String address;
    String city;
    String zip;
    String state;
    Set<OrderLineResponse> orderLines = new HashSet<>();


    public OrderResponse(OrderEntity orderEntity) {
        this.address = orderEntity.getAddress();
        this.city = orderEntity.getCity();
        this.zip = orderEntity.getZip();
        this.state = orderEntity.getState();

        orderEntity.getOrderLines().forEach(orderLineEntity -> {
            orderLines.add(new OrderLineResponse(orderLineEntity));
        });

    }

}
