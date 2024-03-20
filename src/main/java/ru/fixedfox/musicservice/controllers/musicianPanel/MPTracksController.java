package ru.fixedfox.musicservice.controllers.musicianPanel;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.dto.EditCreatorInItemDto;
import ru.fixedfox.musicservice.dto.EditItemNameDto;
import ru.fixedfox.musicservice.entity.Track;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.CreatorService;
import ru.fixedfox.musicservice.services.GenreService;
import ru.fixedfox.musicservice.services.TrackService;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/musician_panel/tracks")
public class MPTracksController {

    private final CreatorService creatorService;
    private final TrackService trackService;
    private final GenreService genreService;
    private final UserDetailsServiceImpl userDetailsService;

    public MPTracksController(CreatorService creatorService, TrackService trackService, GenreService genreService, UserDetailsServiceImpl userDetailsService) {
        this.creatorService = creatorService;
        this.trackService = trackService;
        this.genreService = genreService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String getMusicianPanelSongs(Model model) {
        var userName = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("tracks", userDetailsService.loadNameWithTracksByUsername(userName));
        return "musician_panel/tracks";
    }

    @GetMapping("/{trackId}")
    public String getTrackByIdPage(@PathVariable Long trackId, Model model) {
        model.addAttribute("track", trackService.findTrackById(trackId));
        var userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("creators", creatorService.findCreatorsByUserId(userId));
        return "musician_panel/tracks/track";
    }

    @PostMapping("/{trackId}")
    public String editTrackNameById(@PathVariable Long trackId,
            @ModelAttribute EditItemNameDto track) {
        track.setItemId(trackId);
        trackService.editTrackNameById(track);
        return String.format("redirect:/musician_panel/tracks/%d", trackId);
    }

    @PostMapping("/addCreator/{trackId}")
    public String addCreatorToTrackById(@PathVariable Long trackId, @ModelAttribute EditCreatorInItemDto track) {
        track.setItemId(trackId);
        track.setCreator(creatorService.getCreatorById(track.getCreatorId()));
        trackService.addCreatorToTrack(track);
        return String.format("redirect:/musician_panel/tracks/%d", trackId);
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
        String username = ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        track.setUser(userDetailsService.
                        findUserByUsername(username));
        trackService.addNewTrack(track);
        return "redirect:/musician_panel/tracks";
    }
    @PostMapping("/delete/{trackId}")
    public String addNewTrack (@PathVariable Long trackId){
        var track = trackService.findTrackById(trackId);
        trackService.deleteTrack(track);
        return "redirect:/musician_panel/tracks";
    }
}
