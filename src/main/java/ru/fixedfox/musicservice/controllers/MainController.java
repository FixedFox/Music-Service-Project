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
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public MainController(UserDetailsServiceImpl userDetailsServiceImpl) {
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

    @GetMapping("/recommendation")
    public String getRecomendationPage() {
        return "recommendation";
    }

    @GetMapping("/subscriptions")
    public String getSubscriptionPage(Model model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("creators", userDetailsServiceImpl.getSubscriptionsByUsername(username));
        return "subscriptions";
    }

    @PostMapping("/subscriptions/remove/{creatorId}")
    public String removeCreatorFromUserSubscriptions(@PathVariable Long creatorId) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        userDetailsServiceImpl.removeCreatorFromUserSubscriptionsById(creatorId, username);
        return "redirect:/subscriptions";
    }
}
