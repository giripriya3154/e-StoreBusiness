package cs.miu.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private String productCode;
    private Integer quantity;
    private String productName;
    private Double unitCost;
    private Double subTotal;


}
