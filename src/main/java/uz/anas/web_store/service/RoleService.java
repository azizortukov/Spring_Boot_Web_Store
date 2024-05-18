package uz.anas.web_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.anas.web_store.entity.Role;
import uz.anas.web_store.entity.enums.RoleName;
import uz.anas.web_store.repo.RoleRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public Role findByName(RoleName name) {
        return roleRepo.findByName(name);
    }

    public List<Role> findAll() {
        return roleRepo.findAll();
    }
}
