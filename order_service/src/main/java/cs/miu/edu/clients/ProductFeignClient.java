package cs.miu.edu.clients;

import cs.miu.edu.clients.config.ProductFeignConfig;
import cs.miu.edu.dto.OrderLineDto;
import cs.miu.edu.dto.OrderResponseDto;
import cs.miu.edu.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE",configuration = ProductFeignConfig.class)
public interface ProductFeignClient {
    @GetMapping
    public ResponseEntity<ProductDto> findProoductByProductCode(@RequestParam(value = "productCode" ,required = false) String productCode);

    @PutMapping("products/supply")
    public ResponseEntity<OrderResponseDto> supplyProduct(@RequestBody List<OrderLineDto> orderDtoList);

    @PutMapping("products/return")
    public ResponseEntity<?> returnBack(@RequestBody List<OrderLineDto> orderLineDtos);

}
