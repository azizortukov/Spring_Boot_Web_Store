package uz.anas.web_store.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.anas.web_store.service.ProductService;

import java.nio.file.Files;
import java.nio.file.Path;
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

    @SneakyThrows
    @GetMapping("/logo")
    public void logo(HttpServletResponse response) {
        String path = System.getProperty("user.dir") + "/mango-logo.png";
        byte[] logo = Files.readAllBytes(Path.of(path));
        response.getOutputStream().write(logo);
    }

}
