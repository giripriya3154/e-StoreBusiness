package cs.miu.edu.account_service.clients;

import cs.miu.edu.account_service.clients.config.BankAccountFeignConfig;
import cs.miu.edu.account_service.dto.BankAccountDto;
import cs.miu.edu.account_service.dto.ResponsePaymentMethod;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BANKACCOUNT-SERVICE",configuration = BankAccountFeignConfig.class)
public interface BankAccountFeignClient {
    @PutMapping("/bankaccounts/verify-purchase")
    public ResponseEntity<ResponsePaymentMethod> verifyPurchase(@RequestBody BankAccountDto bankAccountDto);
}
