package groenendaal.tijs.iprwc_api.image;

import groenendaal.tijs.iprwc_api.image.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
}
