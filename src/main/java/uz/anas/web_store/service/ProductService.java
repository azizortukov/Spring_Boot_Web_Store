package uz.anas.web_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.anas.web_store.entity.Product;
import uz.anas.web_store.model.request.ProductRequestDto;
import uz.anas.web_store.repo.CategoryRepo;
import uz.anas.web_store.repo.ProductRepo;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(UUID id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findByCategory(UUID categoryId) {
        return productRepo.findAllByCategoryId(categoryId);
    }

    public void saveAndRedirect(ProductRequestDto productRequestDto, MultipartFile image)  {
        try {
            productRepo.save(Product.builder()
                    .name(productRequestDto.name())
                    .price(productRequestDto.price())
                    .category(categoryRepo.findCategoryById(productRequestDto.categoryId()))
                    .image(image.getBytes())
                    .build());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Product> findByNullableCategoryId(UUID categoryId) {
        return categoryId == null ? productRepo.findAll() : productRepo.findAllByCategoryId(categoryId);
    }
}
