package groenendaal.tijs.iprwc_api.product;

import groenendaal.tijs.iprwc_api.product.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByName(String name);
}
