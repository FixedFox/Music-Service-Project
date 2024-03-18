package ru.fixedfox.musicservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.services.CreatorService;
import ru.fixedfox.musicservice.services.TrackService;
import ru.fixedfox.musicservice.services.TracklistService;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final TrackService trackService;
    private final CreatorService creatorService;
    private final TracklistService tracklistService;

    public SearchController(TrackService trackService, CreatorService creatorService, TracklistService tracklistService) {
        this.trackService = trackService;
        this.creatorService = creatorService;
        this.tracklistService = tracklistService;
    }

    @GetMapping
    public String getSearchPage() {
        return "search";
    }

    @GetMapping("/tracks")
    public String getSearchTrackPage (@RequestParam String track_name, Model model) {
        model.addAttribute("findTracks", trackService.findTracksByName(track_name));
        return "search/byTrackName";
    }
    @GetMapping("/creators")
    public String getSearchCreatorPage (@RequestParam String creator_name, Model model) {
        model.addAttribute("findCreators", creatorService.findCreatorsByName(creator_name));
        return "search/byCreatorName";
    }
    @GetMapping("/tracklists")
    public String getSearchTracklistPage (@RequestParam String tracklist_name, Model model) {
        model.addAttribute("findTracklists", tracklistService.findTracklistsByName(tracklist_name));
        return "search/byTracklistName";
    }
}
