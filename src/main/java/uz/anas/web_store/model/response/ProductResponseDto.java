package uz.anas.web_store.model.response;

import java.util.UUID;

public record ProductResponseDto(UUID id, String name, Integer price) {

}
