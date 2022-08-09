package cs.miu.edu.repository;

import cs.miu.edu.domain.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ShippingRepo extends JpaRepository<Shipper,Integer> {

  Shipper findAllByShippingCode(String shippingCode);

}
