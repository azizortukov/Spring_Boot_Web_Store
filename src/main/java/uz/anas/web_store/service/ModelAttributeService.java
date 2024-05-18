package uz.anas.web_store.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.anas.web_store.entity.Product;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.model.response.ProductResponseDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModelAttributeService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;

    public String index(UUID categoryId, Model model, HttpSession session) {
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findByNullableCategoryId(categoryId));
        model.addAttribute("orderNotification", session.getAttribute("orderNotification"));
        session.setAttribute("orderNotification", null);

        Map<ProductResponseDto, Integer> basketProducts = orderProductService.getFromSession(session);
        model.addAttribute("basketProducts", basketProducts);
        return "home";
    }

    public String basket(Model model, HttpSession session) {
        Object object = session.getAttribute("basketProducts");
        model.addAttribute("basketProducts", object);
        return "basket";
    }

    @SuppressWarnings("unchecked")
    public String basketAddProduct(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = (Map<ProductResponseDto, Integer>) session.getAttribute("basketProducts");
        Product product = productService.findById(productId);
        if (product != null) {
            basketProducts.put(productService.findDtoById(productId), 1);
        }
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/";
    }

    @SuppressWarnings("unchecked")
    public String basketRemoveProduct(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = (Map<ProductResponseDto, Integer>) session.getAttribute("basketProducts");
        Product product = productService.findById(productId);
        if (product != null) {
            basketProducts.remove(productService.findDtoById(productId));
        }
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/";
    }

    @SuppressWarnings("unchecked")
    public String basketAddAmount(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = (Map<ProductResponseDto, Integer>) session.getAttribute("basketProducts");
        ProductResponseDto dto = productService.findDtoById(productId);
        basketProducts.merge(dto, 1, Integer::sum);
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/basket";
    }

    @SuppressWarnings("unchecked")
    public String basketRemoveAmount(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = (Map<ProductResponseDto, Integer>) session.getAttribute("basketProducts");
        ProductResponseDto dto = productService.findDtoById(productId);
        if (dto != null && basketProducts.get(dto) > 1) {
            basketProducts.put(dto, basketProducts.get(dto) - 1);
        }
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/basket";
    }

    @SuppressWarnings("unchecked")
    public String saveOrder(HttpSession session, User user) {
        Map<ProductResponseDto, Integer> basketProducts = (Map<ProductResponseDto, Integer>) session.getAttribute("basketProducts");
        if (basketProducts == null || basketProducts.isEmpty()) {
            session.setAttribute("orderNotification", false);
        } else {
            orderService.createAndSaveOrder(user, basketProducts);
            session.setAttribute("basketProducts", new HashMap<ProductResponseDto, Integer>());
            session.setAttribute("orderNotification", true);
        }
        return "redirect:/";
    }
}
