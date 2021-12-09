package groenendaal.tijs.iprwc_api.cartItem;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.customer.model.CustomerEntity;
import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    CartItemEntity findByProductEntityAndCustomerEntity(ProductEntity productEntity, CustomerEntity customerEntity);
}
