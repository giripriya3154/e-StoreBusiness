package cs.miu.edu.gateway_service.clientfeign;

import cs.miu.edu.gateway_service.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "PAYMENT-SERVICE", configuration = AccountFeignConfig.class)
//public interface AccountFeignClient {
//    @GetMapping("/accounts/verify")
//    ResponseEntity<AccountDto> accountByEmailAddressforVerification(@RequestParam(value = "email" ) String email);
//}
