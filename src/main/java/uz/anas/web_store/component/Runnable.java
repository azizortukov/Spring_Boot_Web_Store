package uz.anas.web_store.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.anas.web_store.entity.Role;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.entity.enums.RoleName;
import uz.anas.web_store.service.RoleService;
import uz.anas.web_store.service.UserService;

import java.util.Arrays;


@Component
@RequiredArgsConstructor
public class Runnable {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (roleService.findAll().isEmpty()) {
            Arrays.stream(RoleName.values()).forEach(roleName -> {
                roleService.save(Role.builder()
                        .name(roleName)
                        .build());
            });
        }
        if (userService.findAll().isEmpty()) {
            userService.save(User.builder()
                    .firstName("Mango")
                    .lastName("Shop")
                    .email("mango@shop.com")
                    .password(passwordEncoder.encode("123"))
                    .build()
            );
        }
    }

}
