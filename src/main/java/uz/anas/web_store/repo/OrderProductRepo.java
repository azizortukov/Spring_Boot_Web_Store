package uz.anas.web_store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.web_store.entity.OrderProduct;

import java.util.UUID;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct, UUID> {

}
