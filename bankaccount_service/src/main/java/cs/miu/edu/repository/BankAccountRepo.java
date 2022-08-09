package cs.miu.edu.repository;

import cs.miu.edu.domain.AccountType;
import cs.miu.edu.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount,Integer> {


    @Query("select bankaccount from BankAccount  bankaccount where bankaccount.bankAccountNumber=?1 and bankaccount.type =?2 and bankaccount.routingNumber=?3")
  public Optional<BankAccount> getBankaccountByAccountNoAccountTypeRoutingNo(String accountNo, AccountType accountType,Integer routingNo);


}
