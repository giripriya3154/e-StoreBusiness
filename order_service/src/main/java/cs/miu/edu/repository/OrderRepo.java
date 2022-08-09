package cs.miu.edu.repository;
import cs.miu.edu.domain.OrderClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepo extends JpaRepository<OrderClass,Integer> {

}
