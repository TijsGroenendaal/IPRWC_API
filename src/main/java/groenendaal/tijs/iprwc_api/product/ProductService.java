package groenendaal.tijs.iprwc_api.product;

import groenendaal.tijs.iprwc_api.cartItem.CartItemRepository;
import groenendaal.tijs.iprwc_api.cartItem.model.CartItemEntity;
import groenendaal.tijs.iprwc_api.exception.EntityNotFoundException;
import groenendaal.tijs.iprwc_api.exception.NameAlreadyInUseException;
import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import groenendaal.tijs.iprwc_api.product.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            CartItemRepository cartItemRepository
    ) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Page<ProductResponse> getAllProduct(
            int page,
            int size,
            String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        List<ProductResponse> productResponseList = new ArrayList<>();
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        productEntities.forEach(productEntity -> productResponseList.add(new ProductResponse(productEntity)));
        return new PageImpl<>(productResponseList, pageable, productEntities.getTotalElements());
    }

    public ProductResponse getProduct(
            UUID productId
    ) {
        return new ProductResponse(
                productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ProductEntity.class))
        );
    }

    public ProductResponse createProduct(
            ProductEntity productEntity
    ) {
        if (productRepository.existsByName(productEntity.getName())) {
            throw new NameAlreadyInUseException(productEntity.getName());
        }
        return new ProductResponse(productRepository.save(productEntity));
    }

    public ProductResponse updateProduct(
            ProductEntity productEntity,
            UUID productId
    ) {
        final ProductEntity oldProduct = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ProductEntity.class));
        oldProduct.setName(productEntity.getName());
        oldProduct.setBrand(productEntity.getBrand());
        oldProduct.setDescription(productEntity.getDescription());
        oldProduct.setImageUrl(productEntity.getImageUrl());
        oldProduct.setPrice(productEntity.getPrice());
        oldProduct.setQuantity(productEntity.getQuantity());
        return new ProductResponse(productRepository.save(productEntity));
    }

    public void deleteProduct(
            UUID productId
    ) {
        final ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ProductEntity.class));
        cartItemRepository.findAllByProductEntity(product).forEach(cartItemRepository::delete);
        productRepository.deleteById(productId);
    }
}
