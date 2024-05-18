package uz.anas.web_store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.web_store.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    Order findCategoryById(Integer id);

}
