package groenendaal.tijs.iprwc_api.order.model;

import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import groenendaal.tijs.iprwc_api.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter @Setter
public class OrderEntity extends BaseEntity {

    private String address;
    private String city;
    private String zip;
    private String state;

    @ManyToOne(cascade = CascadeType.DETACH)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLineEntity> orderLines;

}
