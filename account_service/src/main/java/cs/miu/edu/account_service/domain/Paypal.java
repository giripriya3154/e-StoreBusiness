package cs.miu.edu.account_service.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("paypal")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Paypal extends PaymentMethod{
    private String emailAddress;
    private String secureKey;



}
