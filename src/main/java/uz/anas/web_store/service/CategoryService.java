package uz.anas.web_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.web_store.entity.Category;
import uz.anas.web_store.repo.CategoryRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

}
