package cs.miu.edu.account_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardDto {
    private String cardNumber;
    private String ccv;
    private LocalDate expiryDate;
    private Double balance;
}
