package uz.anas.web_store.model.request;

public interface UserRequestDto {
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
    String getConfirmPassword();
}
