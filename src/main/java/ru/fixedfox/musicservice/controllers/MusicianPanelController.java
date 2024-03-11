package ru.fixedfox.musicservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fixedfox.musicservice.services.GenreService;

@Controller
@RequestMapping("/musicianpanel")
public class MusicianPanelController {

    private final GenreService genreService;

    public MusicianPanelController(GenreService genreService) {
        this.genreService = genreService;
    }


    @GetMapping()
    public String getMusicianPanel() {
        return "musicianpanel";
    }

    @GetMapping("/genres")
    public String getMusicianPanelGenres(Model model) {

        model.addAttribute("genres", genreService.getAllGenres());
        return "musicianpanel/genres";
    }
}
