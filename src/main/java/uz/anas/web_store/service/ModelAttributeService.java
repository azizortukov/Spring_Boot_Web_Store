package uz.anas.web_store.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.anas.web_store.entity.Product;
import uz.anas.web_store.entity.enums.MethodType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModelAttributeService {

    private final CategoryService categoryService;
    private final ProductService productService;

    @SuppressWarnings("unchecked")
    public String home(UUID categoryId, UUID orderNotification, Model model, HttpSession session) {
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findByNullableCategoryId(categoryId));
        model.addAttribute("orderNotification", orderNotification);

        Object basketProducts = session.getAttribute("basketProducts");
        if (basketProducts != null) {
            model.addAttribute("basketProducts", (Map<Product, Integer>) basketProducts);
        } else {
            session.setAttribute("basketProducts", new HashMap<Product, Integer>());
            model.addAttribute("basketProducts", new HashMap<Product, Integer>());
        }
        return "home";
    }

    @SuppressWarnings("unchecked")
    public String basket(Model model, HttpSession session) {
        Object object = session.getAttribute("basketProducts");
        model.addAttribute("basketProducts",  (Map<Product, Integer>) object);
        return "basket";
    }

//    @SuppressWarnings("unchecked")
//    public String basketProduct(UUID productId, MethodType methodType, Model model, HttpSession session) {
//        Map<Product, Integer> basketProducts = (Map<Product, Integer>) session.getAttribute("basketProducts");
//        switch (methodType) {
//            case ADD: basketProducts.put(productService.findById(productId), 1);
//            case REMOVE: basketProducts.remove(productService.findById(productId));
//        }
//
//        model.addAttribute("basketProducts", basketProducts);
//        session.setAttribute("basketProducts", basketProducts);
//        return "redirect:/";
//    }

    @SuppressWarnings("unchecked")
    public String basketAmount(UUID productId, MethodType methodType, HttpSession session) {
        Map<Product, Integer> basketProducts = (Map<Product, Integer>) session.getAttribute("basketProducts");
        Product product = productService.findById(productId);

        switch (methodType) {
            case ADD: basketProducts.put(product, basketProducts.get(product) + 1);
            case REMOVE: {
                if (basketProducts.get(product) > 1) {
                    basketProducts.put(product, basketProducts.get(product) - 1);
                }
            }
        }

        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/basket";
    }

}
