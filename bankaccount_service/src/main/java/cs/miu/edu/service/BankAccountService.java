package cs.miu.edu.service;

import cs.miu.edu.domain.BankAccount;
import cs.miu.edu.dto.BankAccountDto;
import cs.miu.edu.dto.Status;
import cs.miu.edu.dto.ResponseStatus;
import cs.miu.edu.mapper.Mapper;
import cs.miu.edu.repository.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Autowired
    private Mapper mapper;

    //to save the account
    public BankAccountDto saveBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = mapper.mapToTransaction(bankAccountDto);
        bankAccountRepo.save(bankAccount);
        return bankAccountDto;
    }

    //to verify account
    public ResponseStatus verifyPurchase(BankAccountDto bankAccountDto) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepo.getBankaccountByAccountNoAccountTypeRoutingNo(bankAccountDto.getBankAccountNumber(),
                bankAccountDto.getType(), bankAccountDto.getRoutingNumber());
        if (bankAccountOptional.isEmpty()) {
            System.out.println("Invalid account");
            return new ResponseStatus(Status.FAILURE);
        }
        BankAccount bankAccount = bankAccountOptional.get();
        Double bankAccountBalance = bankAccount.getBalance() - bankAccountDto.getBalance();
        if (bankAccountBalance < 0) {
            System.out.println("Insufficient balance to purchase item");
            return new ResponseStatus(Status.FAILURE);
        }
        bankAccount.setBalance(bankAccountBalance);
        bankAccountRepo.save(bankAccount);
        return new ResponseStatus(Status.SUCCESS);
    }

}
