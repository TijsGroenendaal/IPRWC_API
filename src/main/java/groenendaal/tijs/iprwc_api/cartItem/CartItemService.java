package groenendaal.tijs.iprwc_api.cartItem;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(
            CartItemRepository cartItemRepository
    ) {
        this.cartItemRepository = cartItemRepository;
    }

    public Iterable<CartItemEntity> getAllCartItem() {
        return cartItemRepository.findAll();
    }

    public CartItemEntity getCartItem(
            UUID cartItemId
    ) {
        return cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException(CartItemEntity.class));
    }

    public CartItemEntity createCartItem(
            CartItemEntity cartItemEntity
    ) {
        final CartItemEntity oldCartItem = cartItemRepository.findByProductEntityAndCustomerEntity(cartItemEntity.getProductEntity(), cartItemEntity.getCustomerEntity());

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
        final CartItemEntity oldCartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new EntityNotFoundException(CartItemEntity.class));
        oldCartItem.setQuantity(cartItemEntity.getQuantity());
        return cartItemRepository.save(oldCartItem);
    }

    public void deleteCartItem(
            UUID cartItemId
    ) {
        cartItemRepository.deleteById(cartItemId);
    }
}
