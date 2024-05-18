package uz.anas.web_store.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.anas.web_store.service.ModelAttributeService;

import java.util.UUID;

@Controller
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final ModelAttributeService modelAttributeService;

    @GetMapping
    public String basket(Model model, HttpSession session) {
        return modelAttributeService.basket(model, session);
    }

    @GetMapping("/add/{productId}")
    public String addProduct(@PathVariable UUID productId, HttpSession session) {
        return modelAttributeService.basketAddProduct(productId, session);
    }

    @GetMapping("/remove/{productId}")
    public String removeProduct(@PathVariable UUID productId, HttpSession session) {
        return modelAttributeService.basketRemoveProduct(productId, session);
    }

    @GetMapping("/addAmount/{productId}")
    public String addAmountProduct(@PathVariable UUID productId, HttpSession session) {
        return modelAttributeService.basketAddAmount(productId, session);
    }

    @GetMapping("/removeAmount/{productId}")
    public String removeAmountProduct(@PathVariable UUID productId, HttpSession session) {
        return modelAttributeService.basketRemoveAmount(productId, session);
    }

}
