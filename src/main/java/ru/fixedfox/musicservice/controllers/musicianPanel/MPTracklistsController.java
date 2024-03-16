package ru.fixedfox.musicservice.controllers.musicianPanel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.services.GenreService;
import ru.fixedfox.musicservice.services.TracklistService;
import ru.fixedfox.musicservice.services.TracklistTypeService;

@Controller
@RequestMapping("/musician_panel/tracklists")
public class MPTracklistsController {

    private final TracklistService tracklistService;
    private final GenreService genreService;
    private final TracklistTypeService tracklistTypeService;

    public MPTracklistsController(TracklistService tracklistService, GenreService genreService, TracklistTypeService tracklistTypeService) {
        this.tracklistService = tracklistService;
        this.genreService = genreService;
        this.tracklistTypeService = tracklistTypeService;
    }

    @GetMapping
    public String getAllTracklistsPage(Model model) {
        model.addAttribute("tracklists", tracklistService.getAllTracklists());
        return "musician_panel/tracklists";
    }

    @GetMapping("/{id}")
    public String getTraclistPageById(@PathVariable Long id, Model model) {

        return "musician_panel/tracklists/tracklist";
    }

    @GetMapping("/addNewTracklist")
    public String getCreateNewTracklistPage(Model model) {
        model.addAttribute("tracklist_types", tracklistTypeService.getAllTracklistTypes());
        model.addAttribute("genres", genreService.getAllGenres());
        return "musician_panel/tracklists/addNewTracklist";
    }

//    @PostMapping("/addNewTracklist")
//    public String addNewTracklist(@RequestParam String name,
//                                  @RequestParam String tracklist_type,
//                                  @RequestParam String genre) {
//        var tracklist = new TracklistCreateDto();
//        tracklist.setName(name);
//        tracklist.setTracklist_type(tracklist_type);
//        tracklist.setGenre(genre);
//        tracklistService.addNewTracklist(tracklist);
//        return "redirect:/musician_panel/tracklists";
//    }
}
