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
public class UserEntity extends BaseEntity {
    private String username;

    private String password;

    private Role role;

    @JsonManagedReference("product-user")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userEntity")
    private List<CartItemEntity> cartItemEntity;

}
