package fr.hey.toolspringsecuritydemo.repository;

import fr.hey.toolspringsecuritydemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
