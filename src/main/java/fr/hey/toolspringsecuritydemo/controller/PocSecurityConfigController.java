package fr.hey.toolspringsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poc")
public class PocSecurityConfigController {

    @GetMapping("/all")
    public String showViewForAll() {
        return "PocSecurityConfig/viewForAll";
    }

    @GetMapping("/auth")
    public String showViewForAuth() {
        return "PocSecurityConfig/viewForAuth";
    }

    @GetMapping("/admin")
    public String showViewForAdmin() {
        return "PocSecurityConfig/viewForAdmin";
    }

    @GetMapping("/user")
    public String showViewForUser() {
        return "PocSecurityConfig/viewForUser";
    }
}
