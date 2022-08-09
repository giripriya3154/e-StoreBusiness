package cs.miu.edu.account_service.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("creditCard")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreditCard extends PaymentMethod{
    private String cardNumber;
    private String ccv;
    private LocalDate expiryDate;
}
