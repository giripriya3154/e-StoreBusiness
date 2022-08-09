package cs.miu.edu.repository;

import cs.miu.edu.domain.Paypal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PaypalRepo extends JpaRepository<Paypal,Integer> {

    @Query("select paypal from Paypal  paypal where paypal.emailAddress=?1 and paypal.secureKey=?2")
   public Optional<Paypal> getPaypal(String email,String secureKey);



}
