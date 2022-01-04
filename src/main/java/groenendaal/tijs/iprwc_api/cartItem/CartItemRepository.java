package groenendaal.tijs.iprwc_api.cartItem;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
    CartItemEntity findByProductEntityAndUserEntity(ProductEntity productEntity, UserEntity userEntity);
    Iterable<CartItemEntity> findByUserEntity(UserEntity userEntity);
    Optional<CartItemEntity> findByIdAndUserEntity(UUID cartItemId, UserEntity userEntity);
}
