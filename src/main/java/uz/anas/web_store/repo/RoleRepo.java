package uz.anas.web_store.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.anas.web_store.entity.Role;

import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {



}