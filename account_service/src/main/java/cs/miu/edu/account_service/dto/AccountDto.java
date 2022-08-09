package cs.miu.edu.account_service.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import cs.miu.edu.account_service.domain.Address;
import cs.miu.edu.account_service.domain.PaymentMethod;
import cs.miu.edu.account_service.domain.RoleType;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Address address;
    private String paymentMethod;
    private RoleType roleType;
    private String password;
    private  PaymentMethod preferredMethod;
    private String preferred;
    private List<PaymentMethod> paymentMethodList;
    private BankAccountDto bankAccount;
    private PaypalDto paypal;
    private CreditCardDto creditCard;
}
