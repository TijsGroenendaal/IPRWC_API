package groenendaal.tijs.iprwc_api.product.model;

import groenendaal.tijs.iprwc_api.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class ProductEntity extends BaseEntity {

    private String name;

    private int quantity;

    private float price;

    private String description;

    private String imageUrl;

    private String brand;

}
