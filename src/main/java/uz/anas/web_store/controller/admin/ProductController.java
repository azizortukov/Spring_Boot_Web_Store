package uz.anas.web_store.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.anas.web_store.model.request.ProductRequestDto;
import uz.anas.web_store.service.CategoryService;
import uz.anas.web_store.service.ProductService;

@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/admin/product")
    public String productPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "product";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute ProductRequestDto productRequestDto, @RequestParam MultipartFile image, Model model) {
        productService.saveAndRedirect(productRequestDto, image);
        return "redirect:/";
    }
}
