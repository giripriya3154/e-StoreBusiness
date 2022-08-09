package cs.miu.edu.account_service.service;

import cs.miu.edu.account_service.clients.TransactionFeignClient;
import cs.miu.edu.account_service.domain.*;
import cs.miu.edu.account_service.dto.*;
import cs.miu.edu.account_service.clients.BankAccountFeignClient;
import cs.miu.edu.account_service.clients.CreditCardFeignClient;
import cs.miu.edu.account_service.clients.PaypalFeignClient;
import cs.miu.edu.account_service.exceptions.ApiException;
import cs.miu.edu.account_service.exceptions.PaymentVerificationException;
import cs.miu.edu.account_service.mapper.Mapper;
import cs.miu.edu.account_service.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private Mapper mapper;
    @Autowired
    private PaypalFeignClient paypalFeignClient;
    @Autowired
    private CreditCardFeignClient creditCardFeignClient;
    @Autowired
    private BankAccountFeignClient bankAccountFeignClient;
    @Autowired
    private TransactionFeignClient transactionFeignClient;

    public AccountDto findAccountByEmail(String email) {

        Account account = accountRepo.findAccountByEmailAddress(email).get();
        AccountDto accountDto = AccountDto.builder()
                .accountId(account.getAccountId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .address(account.getAddress())
                .emailAddress(account.getEmailAddress())
                .preferredMethod(account.getPreferredMethod())
                .paymentMethodList(account.getPaymentMethodList())

                .paymentMethod(account.getPreferredMethod().getClass().getSimpleName())
                .build();
        return accountDto;
    }

    public AccountDto findAccountByEmailforVerification(String email) {

        Account account = accountRepo.findAccountByEmailAddress(email).get();
        AccountDto accountDto = AccountDto.builder()
                .roleType(account.getRoleType())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .emailAddress(account.getEmailAddress())
                .password(account.getPassword())
                .build();
        System.out.println(accountDto);
        System.out.println(accountDto.getPassword());
        System.out.println(accountDto.getEmailAddress());
        System.out.println(accountDto.getRoleType());
        return accountDto;
    }

    private PaymentMethod paymentMethodForPaypal(AccountDto accountDto) {
        PaypalDto paypalDto = accountDto.getPaypal();
        paypalDto.setBalance(0.0);
        ResponseEntity<ResponsePaymentMethod> response = paypalFeignClient.verifyPurchase(paypalDto);
        if (response.getBody().getStatus() == Status.FAILURE) {
            throw PaymentVerificationException.builder().errorMessage("verification failed").build();

        }
        PaymentMethod paymentMethod = Paypal.builder()
                .secureKey(paypalDto.getSecureKey())
                .emailAddress(paypalDto.getEmailAddress())
                .build();
        return paymentMethod;
    }

    private PaymentMethod paymentMethodForCreditCard(AccountDto accountDto) {
        CreditCardDto creditCardDto = accountDto.getCreditCard();
        creditCardDto.setBalance(0.0);
        ResponseEntity<ResponsePaymentMethod> response = creditCardFeignClient.check(creditCardDto);
        if (response.getBody().getStatus() == Status.FAILURE) {
            throw PaymentVerificationException.builder().errorMessage("verification failed").build();
        }
        PaymentMethod paymentMethod = CreditCard.builder()
                .cardNumber(creditCardDto.getCardNumber())
                .ccv(creditCardDto.getCcv())
                .expiryDate(creditCardDto.getExpiryDate())
                .build();
        return paymentMethod;

    }

    private PaymentMethod paymentMethodForBankAccount(AccountDto accountDto) {
        BankAccountDto bankAccountDto = accountDto.getBankAccount();

        bankAccountDto.setBalance(0.0);
        ResponseEntity<ResponsePaymentMethod> response = bankAccountFeignClient.verifyPurchase(bankAccountDto);
        if (response.getBody().getStatus() == Status.FAILURE) {
            throw PaymentVerificationException.builder().errorMessage("verification failed").build();
        }
        PaymentMethod paymentMethod = BankAccount.builder()
                .emailAddress(bankAccountDto.getEmailAddress())
                .bankAccountNumber(bankAccountDto.getBankAccountNumber())
                .type(bankAccountDto.getType())
                .routingNumber(bankAccountDto.getRoutingNumber())
                .build();
        return paymentMethod;
    }

//    private void setPreferredMethod(Account account,PaymentMethod paymentMethod,PaymentMethod preferred){
//
//            account.setPreferredMethod(paymentMethod);
//            preferred = paymentMethod;
//
//    }

    //to create new Account
    public AccountDto createAccount(AccountDto accountDto) {
        if (accountRepo.findAccountByEmailAddress(accountDto.getEmailAddress()).isPresent()) {
            throw new ApiException("account is already exist with this email : " + accountDto.getEmailAddress());
        }
        Account account = mapper.mapToAccount(accountDto);

        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        PaymentMethod preferred = null;

        //if paypal is not null
        if (accountDto.getPaypal() != null) {
            PaymentMethod paymentMethod = paymentMethodForPaypal(accountDto);
            if (accountDto.getPreferred() != null && accountDto.getPreferred().toUpperCase().equals("PAYPAL")) {
                account.setPreferredMethod(paymentMethod);
                preferred = paymentMethod;
            }
            paymentMethodList.add(paymentMethod);
        }
        //if credit card is not null
        if (accountDto.getCreditCard() != null) {
            PaymentMethod paymentMethod = paymentMethodForCreditCard(accountDto);
            if (accountDto.getPreferred() != null && accountDto.getPreferred().toUpperCase().equals("CREDITCARD")) {
                account.setPreferredMethod(paymentMethod);
                preferred = paymentMethod;
            }
            paymentMethodList.add(paymentMethod);
        }
        //if Bank account is not null
        if (accountDto.getBankAccount() != null) {
            PaymentMethod paymentMethod = paymentMethodForBankAccount(accountDto);
            if (accountDto.getPreferred() != null && accountDto.getPreferred().toUpperCase().equals("BANKACCOUNT")) {
                account.setPreferredMethod(paymentMethod);
                preferred = paymentMethod;
            }
            paymentMethodList.add(paymentMethod);
        }
        if (paymentMethodList.size() > 1 && preferred == null) {
            throw PaymentVerificationException.builder()
                    .errorMessage("Preferred method is not choosen").build();
        }
        if (paymentMethodList.size() == 1 && preferred == null) {
            account.setPreferredMethod(paymentMethodList.get(0));
            preferred = paymentMethodList.get(0);
        }
        account.setPaymentMethodList(paymentMethodList);
        account.setRoleType(RoleType.USER);
        accountRepo.save(account);
        return AccountDto.builder()
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .emailAddress(accountDto.getEmailAddress())
                .preferredMethod(preferred)
                .paymentMethodList(paymentMethodList).build();
    }

    public AccountDto findAccountById(Integer id) {
        Account account = accountRepo.findAccountByAccountId(id).get();
        AccountDto accountDto = mapper.mapToDto(account);
        return accountDto;
    }


    //to get purchase method
    private PaymentMethod getPaymentMethod(PurchaseDto purchaseDto, Account account) {
        PaymentMethod paymentMethod = null;
        if (purchaseDto.getPurchaseMethod() == null) {
            paymentMethod = account.getPreferredMethod();
        } else {
            List<PaymentMethod> paymentMethodList = account.getPaymentMethodList();
            for (PaymentMethod paymentMethod1 : paymentMethodList) {
                if (paymentMethod1.getClass().getSimpleName().equalsIgnoreCase(purchaseDto.getPurchaseMethod())) {
                    paymentMethod = paymentMethod1;
                    break;
                }
            }
        }

        if (paymentMethod == null) {
            throw PaymentVerificationException.builder()
                    .errorMessage("Account with this purchase method is not found")
                    .build();
        }
        return paymentMethod;
    }

    private Status verifyPurchaseForBankAccount(PurchaseDto purchaseDto, PaymentMethod paymentMethod) {
        BankAccount bankAccount = (BankAccount) paymentMethod;
        BankAccountDto bankAccountDto = BankAccountDto.builder()
                .bankAccountNumber(bankAccount.getBankAccountNumber())
                .emailAddress(bankAccount.getEmailAddress())
                .routingNumber(bankAccount.getRoutingNumber())
                .balance(purchaseDto.getBalance())
                .type(bankAccount.getType())
                .build();
        ResponseEntity<ResponsePaymentMethod> response = bankAccountFeignClient.verifyPurchase(bankAccountDto);
        return response.getBody().getStatus();

    }

    private Status verifyPurchaseForPaypal(PurchaseDto purchaseDto, PaymentMethod paymentMethod) {
        Paypal bankAccount = (Paypal) paymentMethod;
        PaypalDto paypalDto = PaypalDto.builder()
                .emailAddress(bankAccount.getEmailAddress())
                .secureKey(bankAccount.getSecureKey())
                .balance(purchaseDto.getBalance())
                .build();
        ResponseEntity<ResponsePaymentMethod> response = paypalFeignClient.verifyPurchase(paypalDto);
        return response.getBody().getStatus();

    }

    private Status verifyPurchaseForCreditCard(PurchaseDto purchaseDto, PaymentMethod paymentMethod) {
        CreditCard creditCard = (CreditCard) paymentMethod;
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .balance(purchaseDto.getBalance())
                .cardNumber(creditCard.getCardNumber())
                .expiryDate(creditCard.getExpiryDate())
                .ccv(creditCard.getCcv())
                .build();
        ResponseEntity<ResponsePaymentMethod> response = creditCardFeignClient.check(creditCardDto);
        return response.getBody().getStatus();

    }

    //for the purchase verification from paymentMethod
    public ResponsePaymentMethod purchaseOrderVerify(PurchaseDto purchaseDto) {
        System.out.println(purchaseDto.getEmailAddress());
        Optional<Account> accountOptional = accountRepo.findAccountByEmailAddress(purchaseDto.getEmailAddress());
        if(accountOptional.isEmpty()){
            throw new ApiException("Something went wrong please try again");
        }
        Account account = accountOptional.get();
        PaymentMethod paymentMethod = getPaymentMethod(purchaseDto, account);
        String paymentMethodName = paymentMethod.getClass().getSimpleName();
        //purchase verify for bank paypal creditcard
        Status status = null;
        if (paymentMethodName.toLowerCase().equals("bankaccount"))
            status = verifyPurchaseForBankAccount(purchaseDto, paymentMethod);
        else if (paymentMethodName.toLowerCase().equals("paypal"))
            status = verifyPurchaseForPaypal(purchaseDto, paymentMethod);
        else
            status = verifyPurchaseForCreditCard(purchaseDto, paymentMethod);
        Integer accountId = null;
        if (status == Status.SUCCESS) {
            accountId = account.getAccountId();
        }
        return ResponsePaymentMethod.builder()
                .accountId(accountId)
                .status(status).build();

    }

    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        ResponseEntity<TransactionDto> transactionResponse = transactionFeignClient.saveTransaction(transactionDto);
        transactionDto.setTransactionCode(transactionResponse.getBody().getTransactionCode());
        return transactionDto;
    }
}
