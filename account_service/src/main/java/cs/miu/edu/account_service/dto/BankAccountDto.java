package cs.miu.edu.account_service.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
//import cs.miu.edu.domain.AccountType;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountDto {
    private Integer routingNumber;
    private String bankAccountNumber;
    private String emailAddress;
    private String type;
    private Double balance;
}
