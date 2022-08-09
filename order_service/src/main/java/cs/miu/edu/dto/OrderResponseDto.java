package cs.miu.edu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto {
    private Status status;
    private String transactionCode;
    private String shipperCode;
    private List<String> errorMessages;
    private List<OrderLineDto> orderLines;
    private Double total;
    private Integer accountId;

}
