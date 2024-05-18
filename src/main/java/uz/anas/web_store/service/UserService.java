package uz.anas.web_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.entity.enums.RoleName;
import uz.anas.web_store.model.request.UserRequestDto;
import uz.anas.web_store.repo.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public boolean existUser(String email) {
        return userRepo.existsByEmail(email);
    }

    public boolean notEqualPassword(UserRequestDto userRequestDto) {
        return !userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword());
    }

    public void saveUser(UserRequestDto user) {
        userRepo.save(User.builder()
                .roles(List.of(roleService.findByName(RoleName.ROLE_USER)))
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build());
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User save(User user) {
         return userRepo.save(user);
    }

    public UserDetails findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
