package groenendaal.tijs.iprwc_api.product;

import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping()
    public Page<ProductEntity> getAllProduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return productService.getAllProduct(page, size, sort);
    }

    @GetMapping("/{productId}")
    public ProductEntity getProduct(
            @PathVariable UUID productId
    ) {
        return productService.getProduct(productId);
    }

    @PostMapping()
    public ProductEntity createProduct(
            @RequestBody ProductEntity productEntity
    ) {
        return productService.createProduct(productEntity);
    }

    @PatchMapping("/{productId}")
    public ProductEntity updateProduct(
            @RequestBody ProductEntity productEntity,
            @PathVariable UUID productId
    ) {
        return productService.updateProduct(productEntity, productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(
            @PathVariable UUID productId
    ) {
        productService.deleteProduct(productId);
    }
}
