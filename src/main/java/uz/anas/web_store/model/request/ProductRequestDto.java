package uz.anas.web_store.model.request;

import java.util.UUID;

public record ProductRequestDto(String name, Integer price, UUID categoryId) {
}
