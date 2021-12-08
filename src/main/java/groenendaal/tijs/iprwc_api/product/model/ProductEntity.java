package groenendaal.tijs.iprwc_api.product.model;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.image.model.ImageEntity;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productEntity", orphanRemoval = true)
    private List<ImageEntity> imageEntityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productEntity", orphanRemoval = true)
    private List<CartItemEntity> cartItemEntity;

}
