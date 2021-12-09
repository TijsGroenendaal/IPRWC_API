package groenendaal.tijs.iprwc_api.product.model;

import lombok.Value;

@Value
public class ProductResponse {

   String name;
   int quantity;
   float price;
   String description;

   public ProductResponse(ProductEntity productEntity) {
      this.description = productEntity.getDescription();
      this.name = productEntity.getName();
      this.price = productEntity.getPrice();
      this.quantity = productEntity.getQuantity();
   }
}
