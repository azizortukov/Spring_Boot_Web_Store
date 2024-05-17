package uz.anas.web_store.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.anas.web_store.entity.Category;
import uz.anas.web_store.entity.Role;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.service.CategoryService;
import uz.anas.web_store.service.RoleService;
import uz.anas.web_store.service.UserService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Runnable {

    private final UserService userService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (userService.findAll().isEmpty()) {
            roleService.save(new Role(UUID.randomUUID(), "ROLE_USER"));
            Role roleAdmin = roleService.save(new Role(UUID.randomUUID(), "ROLE_ADMIN"));

            userService.save(new User(
                UUID.randomUUID(), "azizortukov@gmail.com", passwordEncoder.encode("123"),
                    "Aziz", "Ortukov", List.of(roleAdmin)
            ));
        }
        if (categoryService.findAll().isEmpty()) {
            categoryService.save(new Category(UUID.randomUUID(), "Ichimliklar"));
            categoryService.save(new Category(UUID.randomUUID(), "Yeguliklar"));
            categoryService.save(new Category(UUID.randomUUID(), "Kiyimliklar"));
        }

    }


}
