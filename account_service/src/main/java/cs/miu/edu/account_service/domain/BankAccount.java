package cs.miu.edu.account_service.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("bankAccount")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BankAccount extends  PaymentMethod{
    private Integer routingNumber;
    private String bankAccountNumber;
    private String emailAddress;
    private String type;
}
