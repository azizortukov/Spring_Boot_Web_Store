package uz.anas.web_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.web_store.entity.User;
import uz.anas.web_store.repo.UserRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User findByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

}
