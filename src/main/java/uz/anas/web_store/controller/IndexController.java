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
public class IndexController {

    private final ModelAttributeService modelAttributeService;
    private final HttpSession httpSession;

    @GetMapping
    public String index(@RequestParam(required = false) UUID categoryId, Model model) {
        return modelAttributeService.index(categoryId, model, httpSession);
    }

}
