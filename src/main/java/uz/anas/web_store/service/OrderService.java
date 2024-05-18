package uz.anas.web_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.web_store.entity.Order;
import uz.anas.web_store.entity.OrderProduct;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.entity.enums.Status;
import uz.anas.web_store.model.response.ProductResponseDto;
import uz.anas.web_store.repo.OrderRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderProductService orderProductService;

    public void createAndSaveOrder(User user, Map<ProductResponseDto, Integer> basketProducts) {
        List<OrderProduct> orderProducts = orderProductService.saveListOrderProduct(basketProducts);
        orderRepo.save(Order.builder()
                .user(user)
                .orderProducts(orderProducts)
                .dateTime(LocalDateTime.now())
                .status(Status.NEW)
                .build());
    }
}
