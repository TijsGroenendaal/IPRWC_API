package groenendaal.tijs.iprwc_api.cartItem;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.customer.UserRepository;
import groenendaal.tijs.iprwc_api.customer.model.UserEntity;
import groenendaal.tijs.iprwc_api.exception.EntityNotFoundException;
import groenendaal.tijs.iprwc_api.exception.InvalidJwtException;
import groenendaal.tijs.iprwc_api.helper.RelationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartItemService(
            CartItemRepository cartItemRepository,
            UserRepository userRepository
    ) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    public Iterable<CartItemEntity> getAllCartItem() {
        final UserEntity userEntity = userRepository.findById(RelationHelper.getUserId())
                .orElseThrow(InvalidJwtException::new);
        return cartItemRepository.findByUserEntity(userEntity);
    }

    public CartItemEntity getCartItem(
            UUID cartItemId
    ) {
        final UserEntity userEntity = userRepository.findById(RelationHelper.getUserId())
                .orElseThrow(InvalidJwtException::new);

        return cartItemRepository.findByIdAndUserEntity(cartItemId, userEntity)
                .orElseThrow(() -> new EntityNotFoundException(CartItemEntity.class));
    }

    public CartItemEntity createCartItem(
            CartItemEntity cartItemEntity
    ) {
        final UserEntity userEntity = userRepository.findById(RelationHelper.getUserId())
                .orElseThrow(InvalidJwtException::new);
        cartItemEntity.setUserEntity(userEntity);

        final CartItemEntity oldCartItem = cartItemRepository.findByProductEntityAndUserEntity(
                cartItemEntity.getProductEntity(), cartItemEntity.getUserEntity());

        if (oldCartItem != null) {
            oldCartItem.setQuantity(cartItemEntity.getQuantity() + oldCartItem.getQuantity());
            cartItemRepository.save(oldCartItem);
            return oldCartItem;
        }

        return cartItemRepository.save(cartItemEntity);
    }

    public CartItemEntity updateCartItem(
            CartItemEntity cartItemEntity,
            UUID cartItemId
    ) {
        final CartItemEntity oldCartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(CartItemEntity.class));
        oldCartItem.setQuantity(cartItemEntity.getQuantity());
        return cartItemRepository.save(oldCartItem);
    }

    public void deleteCartItem(
            UUID cartItemId
    ) {
        cartItemRepository.deleteById(cartItemId);
    }
}
