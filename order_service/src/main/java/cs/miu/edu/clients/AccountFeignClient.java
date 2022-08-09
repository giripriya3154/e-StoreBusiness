package cs.miu.edu.clients;


import cs.miu.edu.clients.config.AccountFeignConfig;
import cs.miu.edu.clients.config.ProductFeignConfig;
import cs.miu.edu.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "PAYMENT-SERVICE",configuration = AccountFeignConfig.class)
public interface AccountFeignClient {
    @PutMapping("/accounts/purchase")
    public ResponseEntity<AccountResponseDto> purchaseOrder(@RequestBody PurchaseDto purchaseDto);

    @GetMapping
    public ResponseEntity<AccountDto> accountByEmailAddress(@RequestParam(value = "email" ) String email);

    @PostMapping("/accounts/savetransaction")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto);

}
