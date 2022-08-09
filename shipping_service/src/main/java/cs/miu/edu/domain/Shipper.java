package cs.miu.edu.domain;

import cs.miu.edu.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shipper {
    @Id
    @GeneratedValue
    public Integer shipperId;
    public String shippingCode;
    @Enumerated(EnumType.STRING)
    public Status shippingStatus;
    public Integer orderId;
}
