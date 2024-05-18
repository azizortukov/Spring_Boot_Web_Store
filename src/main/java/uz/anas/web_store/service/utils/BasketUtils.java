package uz.anas.web_store.service.utils;

import org.springframework.stereotype.Component;
import uz.anas.web_store.entity.Product;
import uz.anas.web_store.model.response.ProductResponseDto;

import java.util.Map;

@Component
public class BasketUtils {

    public static boolean isInBasket(Map<ProductResponseDto, Integer> basketProducts, Product product) {
        for (Map.Entry<ProductResponseDto, Integer> entry : basketProducts.entrySet()) {
            if (entry.getKey().id().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

}
