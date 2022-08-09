package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer routingNumber;
    private String bankAccountNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountType type;

}
