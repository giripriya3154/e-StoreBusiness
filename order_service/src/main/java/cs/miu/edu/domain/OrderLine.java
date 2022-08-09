package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class OrderLine {

  private Integer quantity;
  private String productCode;
}
