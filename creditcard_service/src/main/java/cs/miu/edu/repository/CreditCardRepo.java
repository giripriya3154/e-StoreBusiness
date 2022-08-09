package cs.miu.edu.repository;

import cs.miu.edu.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface CreditCardRepo extends JpaRepository<CreditCard,Integer> {

    @Query("select creditcard from CreditCard  creditcard where creditcard.ccv=?3 and creditcard.expiryDate=?2 and creditcard.cardNumber=?1")
  public Optional<CreditCard> getCreditCardByCardNoCardExpiryDateCcvCardLimit(String cardNo, LocalDate expiryDate,String ccv);

}
