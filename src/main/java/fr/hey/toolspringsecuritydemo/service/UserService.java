package fr.hey.toolspringsecuritydemo.service;

import fr.hey.toolspringsecuritydemo.dto.UserDto;
import fr.hey.toolspringsecuritydemo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByLogin(String login);

    List<UserDto> findAllUsers();

    void mockCreateUserIfNotExists(String userInfo, List<String> roleUser);

}
