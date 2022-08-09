package cs.miu.edu.account_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String emailAddress;

    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="account_id")
    private List<PaymentMethod> paymentMethodList;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "preferredMethod_id")
    private PaymentMethod preferredMethod;
}
