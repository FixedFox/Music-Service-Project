package ru.fixedfox.musicservice.controllers.musicianPanel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.services.GenreService;
import ru.fixedfox.musicservice.services.TrackService;

@Controller
@RequestMapping("/musician_panel")
public class MPMenuController {

    private final GenreService genreService;
    private final TrackService trackService;

    public MPMenuController(GenreService genreService, TrackService trackService) {
        this.genreService = genreService;
        this.trackService = trackService;
    }


    @GetMapping
    public String getMusicianPanel() {
        return "musician_panel";
    }
}
