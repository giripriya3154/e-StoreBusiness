package cs.miu.edu.account_service.controller;

import cs.miu.edu.account_service.domain.Account;
import cs.miu.edu.account_service.dto.AccountDto;
import cs.miu.edu.account_service.dto.ApiErrorResponse;
import cs.miu.edu.account_service.dto.PurchaseDto;
import cs.miu.edu.account_service.dto.TransactionDto;
import cs.miu.edu.account_service.exceptions.PaymentVerificationException;
import cs.miu.edu.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> accountByEmailAddress(@RequestParam(value = "email" ) String email){

    return new  ResponseEntity<>(accountService.findAccountByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> accountByEmailAddressforVerification(@RequestParam(value = "email" ) String email){

        return new  ResponseEntity<>(accountService.findAccountByEmailforVerification(email),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AccountDto accountByaccountId(@PathVariable(value="id") Integer id){
        return accountService.findAccountById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto){
        try{
            ResponseEntity<?> response= new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.OK);
            return response;
        }catch (PaymentVerificationException ex){
            ResponseEntity<?> response= new ResponseEntity<>(ApiErrorResponse.builder().error(ex.getErrorMessage()).build(), HttpStatus.OK);
            return response;
        }

    }

    @PutMapping("/purchase")
    public ResponseEntity<?> purchaseOrder(@RequestBody PurchaseDto purchaseDto){
        try {
            ResponseEntity<?> response= new ResponseEntity<>(accountService.purchaseOrderVerify(purchaseDto) ,HttpStatus.OK)  ;
            return response;
        }catch (PaymentVerificationException ex){
            ResponseEntity<?> response= new ResponseEntity<>(ApiErrorResponse.builder().error(ex.getErrorMessage()).build(), HttpStatus.OK);
            return response;
        }

    }

    @PostMapping("/savetransaction")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionDto transactionDto){
        ResponseEntity<?> response= new ResponseEntity<>(accountService.saveTransaction(transactionDto),HttpStatus.OK);
        return response;
    }
}
