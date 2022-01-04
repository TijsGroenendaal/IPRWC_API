package groenendaal.tijs.iprwc_api.order.model;

import lombok.Value;

import java.util.UUID;

@Value
public class OrderLineResponse {

    UUID id;
    int amount;
    UUID productId;

    public OrderLineResponse(OrderLineEntity orderLineEntity) {
        this.id = orderLineEntity.getId();
        this.amount = orderLineEntity.getAmount();
        this.productId = orderLineEntity.getProduct().getId();
    }
}
