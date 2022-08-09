package cs.miu.edu.mapper;

import cs.miu.edu.domain.BankAccount;
import cs.miu.edu.dto.BankAccountDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public BankAccountDto mapToDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = BankAccountDto.builder()
                .balance(bankAccount.getBalance())
                .firstName(bankAccount.getFirstName())
                .lastName(bankAccount.getLastName())
                .routingNumber(bankAccount.getRoutingNumber())
                .emailAddress(bankAccount.getEmailAddress())
                .type(bankAccount.getType())
                .bankAccountNumber(bankAccount.getBankAccountNumber())
                .build();
        return bankAccountDto;
    }

    public BankAccount mapToTransaction(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = BankAccount.builder()
                .firstName(bankAccountDto.getFirstName())
                .lastName(bankAccountDto.getLastName())
                .routingNumber(bankAccountDto.getRoutingNumber())
                .balance(bankAccountDto.getBalance())
                .emailAddress(bankAccountDto.getEmailAddress())
                .type(bankAccountDto.getType())
                .bankAccountNumber(bankAccountDto.getBankAccountNumber())
                .build();
        return bankAccount;
    }
}
