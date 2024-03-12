package ru.fixedfox.musicservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.entity.Genre;
import ru.fixedfox.musicservice.services.GenreService;
import ru.fixedfox.musicservice.services.TrackService;

@Controller
@RequestMapping("/musicianpanel")
public class MusicianPanelController {

    private final GenreService genreService;
    private final TrackService trackService;

    public MusicianPanelController(GenreService genreService, TrackService trackService) {
        this.genreService = genreService;
        this.trackService = trackService;
    }


    @GetMapping()
    public String getMusicianPanel() {
        return "musicianpanel";
    }

    @GetMapping("/tracks")
    public String getMusicianPanelSongs(Model model) {
        model.addAttribute("track", trackService.getTrackById(2L));
        return "musicianpanel/tracks";
    }

    @GetMapping("/genres")
    public String getMusicianPanelGenres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "musicianpanel/genres";
    }

    @PostMapping("/genres/add")
    public String addNewGenre(@RequestParam String genreName) {
        genreService.createNewGenre(new Genre(null, genreName));
        return "redirect:/musicianpanel/genres";
    }

    @GetMapping("/genres/delete/{id}")
    public String deleteGenre(@PathVariable Integer id) {
        genreService.deleteById(id);
        return "redirect:/musicianpanel/genres";
    }

}
