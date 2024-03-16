package ru.fixedfox.musicservice.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/my_collection")
public class CollectionController {

    private final AuthenticationManager authenticationManager;

    public CollectionController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping()
    public String getMyCollection(Model model, Principal principal) {
        model.addAttribute("user", principal);
        return "my_collection";
    }
    @GetMapping("/tracks")
    public String getMyCollectionTracks() {
        return "my_collection/tracks";
    }
    @GetMapping("/tracklists")
    public String getMyCollectionAlbums() {
        return "my_collection/tracklists";
    }
    @GetMapping("/genres")
    public String getMyCollectionGenres() {
        return "my_collection/genres";
    }
    @GetMapping("/creators")
    public String getMyCollectionCreators() {
        return "my_collection/creators";
    }
}
