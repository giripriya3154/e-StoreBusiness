package cs.miu.edu.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import cs.miu.edu.domain.OrderLine;
import lombok.*;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderLineDto {


    private String productCode;
    private Integer quantity;
    private String productName;
    private Double unitCost;
    private Double subTotal;




}
