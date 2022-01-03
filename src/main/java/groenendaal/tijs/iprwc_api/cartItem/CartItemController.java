package groenendaal.tijs.iprwc_api.cartItem;

import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping()
    public Iterable<CartItemEntity> getAllCartItem() {
        return cartItemService.getAllCartItem();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/{cartItemId}")
    public CartItemEntity getCartItem(
            @PathVariable UUID cartItemId
    ) {
        return cartItemService.getCartItem(cartItemId);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping()
    public CartItemEntity createCartItem(
            @RequestBody CartItemEntity cartItemEntity
    ) {
        return cartItemService.createCartItem(cartItemEntity);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PatchMapping("/{cartItemId}")
    public CartItemEntity updateCartItem(
            @RequestBody CartItemEntity cartItemEntity,
            @PathVariable UUID cartItemId
    ) {
        return cartItemService.updateCartItem(cartItemEntity, cartItemId);
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping("/{cartItemId}")
    public void deleteCartItem(
            @PathVariable UUID cartItemId
    ) {
        cartItemService.deleteCartItem(cartItemId);
    }
}
