package ru.fixedfox.musicservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fixedfox.musicservice.dto.EditNameOfUserDto;
import ru.fixedfox.musicservice.dto.EditPasswordDto;
import ru.fixedfox.musicservice.dto.NewCreatorDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.CreatorService;
import ru.fixedfox.musicservice.services.TelegramIntegrationService;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/user_page")
public class UserPageController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final CreatorService creatorService;
    private final PasswordEncoder passwordEncoder;

    private final TelegramIntegrationService telegramService;

    public UserPageController(UserDetailsServiceImpl userDetailsServiceImpl,
                              CreatorService creatorService,
                              PasswordEncoder passwordEncoder,
                              TelegramIntegrationService telegramService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.creatorService = creatorService;
        this.passwordEncoder = passwordEncoder;
        this.telegramService = telegramService;
    }

    @GetMapping
    public String getCurrentUserPage(Model model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("user", userDetailsServiceImpl.loadNameWithCreatorsByUsername(username));
        return "user_page";
    }

    @PostMapping("/become_creator")
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

    @PostMapping("/change_name")
    public String editNameOfUser(@ModelAttribute EditNameOfUserDto user) {
        var currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        user.setUserName(currentUser.getUsername());
        userDetailsServiceImpl.changeNameOfUser(user);
        return "redirect:/user_page";
    }

    @PostMapping("/change_password")
    public String editPassword(@ModelAttribute EditPasswordDto user) {
        var currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        user.setUserName(currentUser.getUsername());
        user.setNewPassword(passwordEncoder.encode(user.getNewPassword()));
        if (passwordEncoder.matches(user.getOldPassword(), currentUser.getPassword())) {
            userDetailsServiceImpl.changePassword(user);
            return "redirect:/logout";
        } else {
            return "redirect:/user_page";
        }
    }

    @PostMapping("/addTelegram")
    public String addTelegramAccount(@RequestParam String telegramName) {
        var currentUser = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();
        userDetailsServiceImpl.addTelegramName(currentUser, telegramName);
        return "redirect:/user_page";
    }

    @GetMapping("/telegramCheck")
    public String checkTelegramConnection() throws JsonProcessingException {
        var currentUser = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        var userFromBase =userDetailsServiceImpl.findUserByUsername(currentUser.getUsername());
        var userTelegramIdLogin = telegramService.takeChatIdByTelegramName(userFromBase.getTelegramNickname());
        if (userTelegramIdLogin != null) {
            userDetailsServiceImpl.setTelegramIdToUsers(userTelegramIdLogin);
        }
        return "redirect:/user_page";
    }

}
