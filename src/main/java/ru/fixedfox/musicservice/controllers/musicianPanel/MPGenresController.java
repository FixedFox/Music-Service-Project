package ru.fixedfox.musicservice.controllers.musicianPanel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.entity.Genre;
import ru.fixedfox.musicservice.services.GenreService;

@Controller
@RequestMapping("/musician_panel/genres")
public class MPGenresController {

    private final GenreService genreService;

    public MPGenresController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String getMusicianPanelGenres(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "musician_panel/genres";
    }

    @PostMapping("/add")
    public String addNewGenre(@RequestParam String genreName) {
        genreService.createNewGenre(new Genre(null, genreName));
        return "redirect:/musician_panel/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Integer id) {
        genreService.deleteById(id);
        return "redirect:/musician_panel/genres";
    }
}
