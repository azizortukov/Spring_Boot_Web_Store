package uz.anas.web_store.service.utils;

import org.springframework.stereotype.Component;
import uz.anas.web_store.entity.Product;

import java.util.Map;

@Component
public class BasketUtils {

    public static boolean isInBasket(Map<Product, Integer> basketProducts, Product product) {
        for (Map.Entry<Product, Integer> entry : basketProducts.entrySet()) {
            if (entry.getKey().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;
    }

}
