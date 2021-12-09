package groenendaal.tijs.iprwc_api.cartItem;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping()
    public Iterable<CartItemEntity> getAllCartItem() {
        return cartItemService.getAllCartItem();
    }

    @GetMapping("/{cartItemId}")
    public CartItemEntity getCartItem(
            @PathVariable UUID cartItemId
    ) {
        return cartItemService.getCartItem(cartItemId);
    }

    @PostMapping()
    public CartItemEntity createCartItem(
            @RequestBody CartItemEntity cartItemEntity
    ) {
        return cartItemService.createCartItem(cartItemEntity);
    }

    @PatchMapping("/{cartItemId}")
    public CartItemEntity updateCartItem(
            @RequestBody CartItemEntity cartItemEntity,
            @PathVariable UUID cartItemId
    ) {
        return cartItemService.updateCartItem(cartItemEntity, cartItemId);
    }

    @DeleteMapping("/{cartItemId}")
    public void deleteCartItem(
            @PathVariable UUID cartItemId
    ) {
        cartItemService.deleteCartItem(cartItemId);
    }
}
