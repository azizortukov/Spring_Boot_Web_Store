package uz.anas.web_store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.web_store.entity.Order;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.entity.enums.Status;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserAndStatusNot(User user, Status status);

}
