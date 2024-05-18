package uz.anas.web_store.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.web_store.entity.OrderProduct;
import uz.anas.web_store.model.response.ProductResponseDto;
import uz.anas.web_store.repo.OrderProductRepo;
import uz.anas.web_store.repo.ProductRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepo orderProductRepo;
    private final ProductRepo productRepo;

    public List<OrderProduct> saveListOrderProduct(Map<ProductResponseDto, Integer> basketProducts) {
        List<OrderProduct> orderProducts = basketProducts.entrySet().stream()
                .map(entry -> OrderProduct.builder()
                        .product(productRepo.findById(entry.getKey().id()).orElseThrow())
                        .amount(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        orderProductRepo.saveAll(orderProducts);
        return orderProducts;
    }

    public Integer getBasketSum(Map<ProductResponseDto, Integer> basketProducts) {
        int sum = 0;
        for (Map.Entry<ProductResponseDto, Integer> entry : basketProducts.entrySet()) {
            sum += entry.getKey().price() * entry.getValue();
        }
        return sum;
    }


    @SuppressWarnings("unchecked")
    public Map<ProductResponseDto, Integer> getFromSession(HttpSession session) {
        Map<ProductResponseDto, Integer> basketProducts = (Map<ProductResponseDto, Integer>) session.getAttribute("basketProducts");
        if (basketProducts == null) {
            basketProducts = new HashMap<>();
            session.setAttribute("basketProducts", basketProducts);
        }
        return basketProducts;
    }
}
