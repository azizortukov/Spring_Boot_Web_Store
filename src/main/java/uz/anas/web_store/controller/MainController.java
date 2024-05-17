package uz.anas.web_store.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.anas.web_store.service.ModelAttributeService;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ModelAttributeService modelAttributeService;
    private final HttpSession httpSession;

    @GetMapping
    public String home(@RequestParam(required = false) UUID categoryId, @RequestParam(required = false) UUID orderNotification, Model model) {
        return modelAttributeService.home(categoryId, orderNotification, model, httpSession);
    }

}
