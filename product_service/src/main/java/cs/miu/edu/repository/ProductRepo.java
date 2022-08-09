package cs.miu.edu.repository;

import cs.miu.edu.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

     Optional<Product> getProductByProductCode(String productCode);


}
