package cs.miu.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class PurchaseDto {
  private String emailAddress;
  private Double balance;
  private String purchaseMethod;
}
