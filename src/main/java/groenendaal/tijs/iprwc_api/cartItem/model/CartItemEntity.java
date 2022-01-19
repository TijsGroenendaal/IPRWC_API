package groenendaal.tijs.iprwc_api.cartItem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import groenendaal.tijs.iprwc_api.model.BaseEntity;
import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class CartItemEntity extends BaseEntity {

    private int quantity;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private ProductEntity productEntity;

    @JsonBackReference("cart-user")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private UserEntity userEntity;

}
