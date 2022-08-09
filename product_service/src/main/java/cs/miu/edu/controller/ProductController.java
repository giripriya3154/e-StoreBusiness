package cs.miu.edu.controller;

import cs.miu.edu.domain.Product;
import cs.miu.edu.dto.OrderLineDto;
import cs.miu.edu.dto.ProductDto;
import cs.miu.edu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAllProduct(@RequestParam(value = "productCode", required = false) String productCode) {
        if (productCode == null || productCode.isEmpty()) {
            ResponseEntity<List<ProductDto>> response = new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
            return response;
        }
        Optional<ProductDto> productDtoOptional = productService.findProductByProductCode(productCode);
        if (productDtoOptional.isEmpty()) {
            ResponseEntity<?> response = new ResponseEntity<>("product with code "+ productCode+" is not found", HttpStatus.NOT_FOUND);
            return response;
        }
        ResponseEntity<ProductDto> response = new ResponseEntity<>(productDtoOptional.get(), HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }


    @PutMapping("/supply")
    public ResponseEntity<?> supplyProduct(@RequestBody List<OrderLineDto> orderLineDtos){
        ResponseEntity<?> response= new ResponseEntity<>(productService.findOrdersPriceByProductCode(orderLineDtos),HttpStatus.OK);
        return response;
    }
    @PutMapping("/return")
    public ResponseEntity<?> returnBack(@RequestBody List<OrderLineDto> orderLineDtos){
        return new ResponseEntity<>(productService.returnOrderProduct(orderLineDtos),HttpStatus.OK);
    }

}
