package cs.miu.edu.clients;

import cs.miu.edu.clients.config.ShippingFeignConfig;
import cs.miu.edu.dto.ShipperDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="SHIPPING-SERVICE", configuration = ShippingFeignConfig.class)
public interface ShippingFeignClient {
    @PostMapping( "/shippings")
    ResponseEntity<ShipperDto> submitToShipper(@RequestBody ShipperDto shipperDto);
}
