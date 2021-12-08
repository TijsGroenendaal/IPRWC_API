package groenendaal.tijs.iprwc_api.product;

import groenendaal.tijs.iprwc_api.exception.EntityNotFoundException;
import groenendaal.tijs.iprwc_api.exception.NameAlreadyInUseException;
import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    public Page<ProductEntity> getAllProduct(
            int page,
            int size,
            String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return productRepository.findAll(pageable);
    }

    public ProductEntity getProduct(
            UUID productId
    ) {
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ProductEntity.class));
    }

    public ProductEntity createProduct(
            ProductEntity productEntity
    ) {
        if (productRepository.existsByName(productEntity.getName())) {
            throw new NameAlreadyInUseException(productEntity.getName());
        }
        return productRepository.save(productEntity);
    }

    public ProductEntity updateProduct(
            ProductEntity productEntity,
            UUID productId
    ) {
        productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException(ProductEntity.class));
        return productRepository.save(productEntity);
    }

    public void deleteProduct(
            UUID productId
    ) {
        productRepository.deleteById(productId);
    }
}
