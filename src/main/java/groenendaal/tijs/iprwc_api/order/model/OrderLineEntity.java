package groenendaal.tijs.iprwc_api.order.model;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.model.BaseEntity;
import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
@NoArgsConstructor
public class OrderLineEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.DETACH)
    private OrderEntity order;

    @OneToOne(cascade = CascadeType.DETACH)
    private ProductEntity product;

    private int amount;

    public OrderLineEntity(ProductEntity productEntity, CartItemEntity cartItemEntity, OrderEntity orderEntity) {
        this.amount = cartItemEntity.getQuantity();
        this.product = productEntity;
        this.order = orderEntity;
    }
}
