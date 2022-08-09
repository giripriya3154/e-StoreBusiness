package cs.miu.edu.account_service.clients;

import cs.miu.edu.account_service.clients.config.PaypalFeignConfig;
import cs.miu.edu.account_service.dto.PaypalDto;
import cs.miu.edu.account_service.dto.ResponsePaymentMethod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYPAL-SERVICE",configuration = PaypalFeignConfig.class)
public interface PaypalFeignClient {
    @PutMapping("/paypals/verify-purchase")
    public ResponseEntity<ResponsePaymentMethod> verifyPurchase(@RequestBody PaypalDto paypalDto);
}
