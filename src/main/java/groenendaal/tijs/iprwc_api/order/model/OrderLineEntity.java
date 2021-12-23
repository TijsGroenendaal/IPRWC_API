package groenendaal.tijs.iprwc_api.order.model;

import groenendaal.tijs.iprwc_api.model.BaseEntity;
import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter @Setter
public class OrderLineEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.DETACH)
    private OrderEntity order;

    @OneToOne(cascade = CascadeType.DETACH)
    private ProductEntity product;

    private int amount;

}
