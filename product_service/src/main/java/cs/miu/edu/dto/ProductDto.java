package cs.miu.edu.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import cs.miu.edu.domain.Category;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Integer productId;
    private String productName;
    private String productCode;
    private Double price;
    private String vendor;
    private Category productCategory;
    private Integer availableUnit;



}
