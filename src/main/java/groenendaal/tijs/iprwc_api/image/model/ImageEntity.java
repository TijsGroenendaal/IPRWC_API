package groenendaal.tijs.iprwc_api.image.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ImageEntity extends BaseEntity {

    private String description;

    @JsonBackReference("product-image")
    @ManyToOne(cascade = CascadeType.DETACH)
    private ProductEntity productEntity;

}
