package cs.miu.edu.account_service.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaypalDto {
    private String emailAddress;
    private String secureKey;
    private Double balance;
}
