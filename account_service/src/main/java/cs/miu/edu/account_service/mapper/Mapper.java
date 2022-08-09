package cs.miu.edu.account_service.mapper;

import cs.miu.edu.account_service.domain.Account;
import cs.miu.edu.account_service.dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public Account mapToAccount(AccountDto accountDto){
        Account account = Account.builder()
                .firstName(accountDto.getFirstName())
                .lastName(accountDto.getLastName())
                .emailAddress(accountDto.getEmailAddress())
                .password(accountDto.getPassword())
                .address(accountDto.getAddress())
                .preferredMethod(accountDto.getPreferredMethod())
                .paymentMethodList(accountDto.getPaymentMethodList())
                .build();
        return account;
    }

    public AccountDto mapToDto(Account account){
        AccountDto accountDto = AccountDto.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .emailAddress(account.getEmailAddress())
                .address(account.getAddress())
                .paymentMethodList(account.getPaymentMethodList())
                .paymentMethod(account.getPreferredMethod().getClass().getSimpleName())
                .build();
        return accountDto;
    }
}
