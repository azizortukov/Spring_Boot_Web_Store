package uz.anas.web_store.model.request;

import java.util.UUID;

public interface ProductRequestDto {
    String getName();
    Integer getPrice();
    UUID getCategoryId();
}
