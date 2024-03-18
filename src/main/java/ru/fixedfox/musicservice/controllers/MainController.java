package ru.fixedfox.musicservice.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.dto.EditNameOfUserDto;
import ru.fixedfox.musicservice.dto.EditPasswordDto;
import ru.fixedfox.musicservice.dto.NewCreatorDto;
import ru.fixedfox.musicservice.dto.NewUserRegistrationDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.CreatorService;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

@Controller
public class MainController {
    private final CreatorService creatorService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public MainController(CreatorService creatorService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.creatorService = creatorService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @GetMapping
    public String getLandingPage() {
        return "index";
    }

    @GetMapping("/mainpage")
    public String getStartPage(Model model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("user", userDetailsServiceImpl.getUserForMainPageByUsename(username));
        return "mainpage";
    }

    @GetMapping("/whatsnew")
    public String getWhatsNewPage() {
        return "whatsnew";
    }

    @GetMapping("/recomendation")
    public String getRecomendation() {
        return "recommendation";
    }
}
