package ru.fixedfox.musicservice.controllers.musicianPanel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fixedfox.musicservice.entity.Track;
import ru.fixedfox.musicservice.services.GenreService;
import ru.fixedfox.musicservice.services.TrackService;

@Controller
@RequestMapping("/musician_panel/tracks")
public class MPTracksController {

    private final TrackService trackService;
    private final GenreService genreService;

    public MPTracksController(TrackService trackService, GenreService genreService) {
        this.trackService = trackService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getMusicianPanelSongs(Model model) {
        model.addAttribute("tracks", trackService.getAllTracksOfCreator());
        return "musician_panel/tracks";
    }

    @GetMapping("/addNewTrack")
    public String addNewTrackPage(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "musician_panel/tracks/addNewTrack";
    }

    @PostMapping("/addNewTrack")
    public String addNewTrack(@RequestParam String track_name,
                              @RequestParam Long count_of_play,
                              @RequestParam String genre) {
        var track = new Track();
        track.setTrackName(track_name);
        track.setCountOfPlays(count_of_play);
        track.setGenre(genreService.getGenreByName(genre));
        trackService.addNewTrack(track);
        return "redirect:/musician_panel/tracks";
    }
}
