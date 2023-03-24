package fr.hey.toolspringsecuritydemo.service.impl;

import fr.hey.toolspringsecuritydemo.dto.UserDto;
import fr.hey.toolspringsecuritydemo.entity.Role;
import fr.hey.toolspringsecuritydemo.entity.User;
import fr.hey.toolspringsecuritydemo.exceptions.UserAlreadyExistException;
import fr.hey.toolspringsecuritydemo.repository.RoleRepository;
import fr.hey.toolspringsecuritydemo.repository.UserRepository;
import fr.hey.toolspringsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());

        if (loginExists(userDto.getLogin())) {
            throw new UserAlreadyExistException("Un utilisateur existe déjà avec ce login : "
                    + userDto.getLogin());
        }

        // Encrypter le mot de passe
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    private boolean loginExists(String login) {

        return findByLogin(login) != null;

    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
