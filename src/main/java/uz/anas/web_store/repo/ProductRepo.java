package uz.anas.web_store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.web_store.entity.Product;
import uz.anas.web_store.model.response.ProductResponseDto;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {

    List<Product> findAllByCategoryId(UUID category);

    ProductResponseDto findDtoById(UUID id);
}
