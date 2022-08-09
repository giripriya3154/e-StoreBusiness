package cs.miu.edu.account_service.clients;

import cs.miu.edu.account_service.clients.config.CreditCardFeignConfig;
import cs.miu.edu.account_service.dto.CreditCardDto;
import cs.miu.edu.account_service.dto.ResponsePaymentMethod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CREDITCARD-SERVICE",configuration = CreditCardFeignConfig.class)
public interface CreditCardFeignClient {

    @PostMapping("/creditcards/verify-purchase")
    public ResponseEntity<ResponsePaymentMethod> check(@RequestBody CreditCardDto creditCardDto);
}
