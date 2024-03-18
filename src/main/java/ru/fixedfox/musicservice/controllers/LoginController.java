package ru.fixedfox.musicservice.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fixedfox.musicservice.dto.NewUserRegistrationDto;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

@Controller
public class LoginController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign_in")
    public String getLoginPage() {
        return "sign_in";
    }

    @GetMapping("/sign_up")
    public String getRegistrationPage() {
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String registerNewUser(@ModelAttribute NewUserRegistrationDto newUser) {
        var passwordHash = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(passwordHash);
        userDetailsServiceImpl.addNewUser(newUser);
        return "redirect:/sign_in";
    }
}
