package groenendaal.tijs.iprwc_api.product;

import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import groenendaal.tijs.iprwc_api.product.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping()
    public Page<ProductResponse> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return productService.getAllProduct(page, size, sort);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/{productId}")
    public ProductEntity getProduct(
            @PathVariable UUID productId
    ) {
        return productService.getProduct(productId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public ProductEntity createProduct(
            @RequestBody ProductEntity productEntity
    ) {
        return productService.createProduct(productEntity);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{productId}")
    public ProductEntity updateProduct(
            @RequestBody ProductEntity productEntity,
            @PathVariable UUID productId
    ) {
        return productService.updateProduct(productEntity, productId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{productId}")
    public void deleteProduct(
            @PathVariable UUID productId
    ) {
        productService.deleteProduct(productId);
    }
}
