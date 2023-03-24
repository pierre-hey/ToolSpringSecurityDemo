package fr.hey.toolspringsecuritydemo.controller;

import fr.hey.toolspringsecuritydemo.dto.UserDto;
import fr.hey.toolspringsecuritydemo.exceptions.UserAlreadyExistException;
import fr.hey.toolspringsecuritydemo.service.UserService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public ModelAndView showRegistrationForm(){
        return new ModelAndView("register","userDto",new UserDto());
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult result, Model model){

      if (result.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "register";
        }

        try {
            userService.saveUser(userDto);
        }catch (final UserAlreadyExistException existException){

            result.rejectValue("login", null, existException.getMessage());
            return "register";
        }

        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
