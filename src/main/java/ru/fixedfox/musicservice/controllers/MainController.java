package ru.fixedfox.musicservice.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.dto.NewCreatorDto;
import ru.fixedfox.musicservice.dto.NewUserRegistrationDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.CreatorService;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

@Controller
public class MainController {

    private final CreatorService creatorService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public MainController(CreatorService creatorService, UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.creatorService = creatorService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getLandingPage() {
        return "index";
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

    @GetMapping("/mainpage")
    public String getStartPage(Model model) {
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "mainpage";
    }

    @GetMapping("/user_page")
    public String getCurrentUserPage(Model model) {
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "user_page";
    }

    @PostMapping("/user_page/become_creator")
    public String addNewCreatorByUser(@ModelAttribute NewCreatorDto newCreatorDto) {
        var currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        newCreatorDto.setUser(currentUser);
        creatorService.addNewCreator(newCreatorDto);
        userDetailsServiceImpl.changeUserAuthority(currentUser, "MUSICIAN");
        return "redirect:/user_page";
    }

    @GetMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @GetMapping("/whatsnew")
    public String getWhatsNewPage() {
        return "whatsnew";
    }

    @GetMapping("/recomendation")
    public String getRecomendation() {
        return "recomendation";
    }

    @GetMapping("/musician_panel/creator/{id}")
    public String getPageWithCreator(@PathVariable Long id, Model model) {
        model.addAttribute("creator", creatorService.getCreatorById(id));
        return "musician_panel/creator";
    }


}
