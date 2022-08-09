package cs.miu.edu.account_service.repository;

import cs.miu.edu.account_service.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {


    @Query("select account from Account  account where account.emailAddress= ?1")
    public Optional<Account> findAccountByEmailAddress(String email);

    public Optional<Account> findAccountByAccountId(Integer id);
}
