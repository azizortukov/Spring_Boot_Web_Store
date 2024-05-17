package uz.anas.web_store.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.anas.web_store.service.ProductService;

import java.util.UUID;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final ProductService productService;

    @GetMapping("/download/{productId}")
    @SneakyThrows
    public void downloadProductImage(@PathVariable UUID productId, HttpServletResponse response) {
        response.getOutputStream().write(productService.findById(productId).getImage());
    }

}
