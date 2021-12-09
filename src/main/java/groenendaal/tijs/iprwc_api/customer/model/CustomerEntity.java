package groenendaal.tijs.iprwc_api.customer.model;

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
public class CustomerEntity extends BaseEntity {
    private String email;

    private String password;

    @JsonManagedReference("product-customer")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customerEntity")
    private List<CartItemEntity> cartItemEntity;

}
