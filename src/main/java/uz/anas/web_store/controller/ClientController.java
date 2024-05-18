package uz.anas.web_store.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.service.ModelAttributeService;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ModelAttributeService modelAttributeService;

    @GetMapping("/my_orders")
    public String myOrders(@AuthenticationPrincipal User user, Model model, HttpSession session) {
        return modelAttributeService.myOrders(user, model, session);
    }

    @GetMapping("/order/confirm")
    public String confirm(@AuthenticationPrincipal User user, HttpSession session){
        return modelAttributeService.saveOrder(session, user);
    }

}
