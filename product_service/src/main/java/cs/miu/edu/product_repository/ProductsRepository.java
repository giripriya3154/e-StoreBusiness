package cs.miu.edu.product_repository;

import cs.miu.edu.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {



    Optional<Product> findByProductCode(String productCode);
}
