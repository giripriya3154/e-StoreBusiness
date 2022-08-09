package cs.miu.edu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    private String transactionCode;
    private Double total;
    private String paymentMethod;
    private Integer orderId;

}
