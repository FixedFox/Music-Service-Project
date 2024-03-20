package ru.fixedfox.musicservice.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.dto.EditTrackMyLibraryDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.CreatorService;
import ru.fixedfox.musicservice.services.TrackService;
import ru.fixedfox.musicservice.services.TracklistService;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final TrackService trackService;
    private final CreatorService creatorService;
    private final TracklistService tracklistService;
    private final UserDetailsServiceImpl userDetailsService;

    public SearchController(TrackService trackService,
                            CreatorService creatorService,
                            TracklistService tracklistService,
                            UserDetailsServiceImpl userDetailsService) {
        this.trackService = trackService;
        this.creatorService = creatorService;
        this.tracklistService = tracklistService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String getSearchPage() {
        return "search";
    }

    @GetMapping("/tracks")
    public String getSearchTrackPage (@RequestParam String name, Model model) {
        var userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        var nameForsearh ="%" + name + "%";
        model.addAttribute("findTracks", trackService.findTracksTracklistsCreatorsByName(nameForsearh, userId));
        return "search/tracks";
    }

    @PostMapping("/tracks/add/{id}")
    public String addTrackToLibrary(@PathVariable Long id) {
        var username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        var track = new EditTrackMyLibraryDto();
        track.setUsername(username);
        track.setTrackId(id);
        userDetailsService.addTrackToMyLibraryById(track);
        return "redirect:/search";
    }
}
