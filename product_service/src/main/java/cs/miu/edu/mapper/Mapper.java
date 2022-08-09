package cs.miu.edu.mapper;

import cs.miu.edu.domain.Product;
import cs.miu.edu.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public ProductDto mapToDto(Product product){
      ProductDto productDto= ProductDto.builder()
              .productId(product.getProductId())
              .availableUnit(product.getAvailableUnit())
              .productCode(product.getProductCode())
              .productCategory(product.getProductCategory())
              .productName(product.getProductName())
              .vendor(product.getVendor())
              .price(product.getPrice())
              .build();
      return productDto;
    }

    public Product mapToProduct(ProductDto productDto){
         Product product= Product.builder()
                .productName(productDto.getProductName())
                .productCode(productDto.getProductCode())
                .availableUnit(productDto.getAvailableUnit())
                .price(productDto.getPrice())
                .vendor(productDto.getVendor())
                .productCategory(productDto.getProductCategory())
                .build();
        return product;
    }
}
