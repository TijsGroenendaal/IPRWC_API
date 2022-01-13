package groenendaal.tijs.iprwc_api.product.model;

import lombok.Value;

import java.util.UUID;

@Value
public class ProductResponse {

   UUID id;
   String name;
   int quantity;
   float price;
   String description;
   String imageUrl;
   String brand;


   public ProductResponse(ProductEntity productEntity) {
      this.id = productEntity.getId();
      this.description = productEntity.getDescription();
      this.name = productEntity.getName();
      this.price = productEntity.getPrice();
      this.quantity = productEntity.getQuantity();
      this.imageUrl = productEntity.getImageUrl();
      this.brand = productEntity.getBrand();
   }
}
