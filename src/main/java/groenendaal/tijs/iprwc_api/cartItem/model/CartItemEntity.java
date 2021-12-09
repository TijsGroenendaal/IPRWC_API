package groenendaal.tijs.iprwc_api.cartItem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import groenendaal.tijs.iprwc_api.customer.model.CustomerEntity;
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

    @JsonBackReference("product-cartItem")
    @ManyToOne(cascade = CascadeType.DETACH)
    private ProductEntity productEntity;

    @JsonBackReference("product-customer")
    @ManyToOne(cascade = CascadeType.DETACH)
    private CustomerEntity customerEntity;

}
