package fr.hey.toolspringsecuritydemo.repository;

import fr.hey.toolspringsecuritydemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}
