package groenendaal.tijs.iprwc_api.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter @Setter
public class ProductEntity extends BaseEntity {

    private String name;

    private int quantity;

    private float price;

    private String description;

    private String imageUrl;

    @JsonIgnore
    @JsonManagedReference("product-cartItem")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "productEntity")
    private List<CartItemEntity> cartItemEntity;

}
