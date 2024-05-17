package uz.anas.web_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.web_store.entity.Role;
import uz.anas.web_store.repo.RoleRepo;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Role save(Role role) {
        return roleRepo.save(role);
    }


}
