package uz.anas.web_store.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uz.anas.web_store.entity.Order;
import uz.anas.web_store.entity.Product;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.model.response.ProductResponseDto;

import java.util.*;

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

        sessionsBasketProducts(Optional.of(model), session);
        return "home";
    }

    public String basket(Model model, HttpSession session) {
        sessionsBasketProducts(Optional.of(model), session);
        return "basket";
    }

    public String basketAddProduct(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = sessionsBasketProducts(Optional.empty(), session);
        Product product = productService.findById(productId);
        if (product != null) {
            basketProducts.put(productService.findDtoById(productId), 1);
        }
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/";
    }

    public String basketRemoveProduct(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = sessionsBasketProducts(Optional.empty(), session);
        Product product = productService.findById(productId);
        if (product != null) {
            basketProducts.remove(productService.findDtoById(productId));
        }
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/";
    }

    public String basketAddAmount(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = sessionsBasketProducts(Optional.empty(), session);
        ProductResponseDto dto = productService.findDtoById(productId);
        basketProducts.merge(dto, 1, Integer::sum);
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/basket";
    }

    public String basketRemoveAmount(UUID productId, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = sessionsBasketProducts(Optional.empty(), session);
        ProductResponseDto dto = productService.findDtoById(productId);
        if (dto != null && basketProducts.get(dto) > 1) {
            basketProducts.put(dto, basketProducts.get(dto) - 1);
        }
        session.setAttribute("basketProducts", basketProducts);
        return "redirect:/basket";
    }

    public String saveOrder(HttpSession session, User user) {
        Map<ProductResponseDto, Integer> basketProducts = sessionsBasketProducts(Optional.empty(), session);
        if (basketProducts.isEmpty()) {
            session.setAttribute("orderNotification", false);
        } else {
            orderService.createAndSaveOrder(user, basketProducts);
            session.setAttribute("basketProducts", new HashMap<ProductResponseDto, Integer>());
            session.setAttribute("orderNotification", true);
        }
        return "redirect:/";
    }

    public String myOrders(User user, Model model, HttpSession session) {
        List<Order> userOrders = orderService.findUserOrders(user);
        model.addAttribute("orders", userOrders);
        sessionsBasketProducts(Optional.of(model), session);
        return "myOrders";
    }

    @SuppressWarnings("unchecked")
    public static Map<ProductResponseDto, Integer> sessionsBasketProducts(Optional<Model> model, HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts;
        Object object = session.getAttribute("basketProducts");
        if (object != null) {
            model.ifPresent(item -> item.addAttribute("basketProducts", object));
            basketProducts = (Map<ProductResponseDto, Integer>) object;
        }else {
            basketProducts = new HashMap<>();
            model.ifPresent(item -> item.addAttribute("basketProducts", basketProducts));
        }
        return basketProducts;
    }
}
