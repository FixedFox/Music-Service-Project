package ru.fixedfox.musicservice.controllers.musicianPanel;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fixedfox.musicservice.dto.EditCreatorInItemDto;
import ru.fixedfox.musicservice.dto.EditItemNameDto;
import ru.fixedfox.musicservice.dto.EditTrackInTracklistDto;
import ru.fixedfox.musicservice.dto.NewTracklistDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.*;

@Controller
@RequestMapping("/musician_panel/tracklists")
public class MPTracklistsController {

    private final TracklistService tracklistService;
    private final GenreService genreService;
    private final TracklistTypeService tracklistTypeService;
    private final CreatorService creatorService;
    private final TrackService trackService;
    private final UserDetailsServiceImpl userDetailsService;

    public MPTracklistsController(TracklistService tracklistService,
                                  GenreService genreService,
                                  TracklistTypeService tracklistTypeService,
                                  CreatorService creatorService,
                                  TrackService trackService,
                                  UserDetailsServiceImpl userDetailsService) {
        this.tracklistService = tracklistService;
        this.genreService = genreService;
        this.tracklistTypeService = tracklistTypeService;
        this.creatorService = creatorService;
        this.trackService = trackService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String getAllTracklistsPage(Model model) {
        model.addAttribute("tracklists", tracklistService.findAllTracklists());
        return "musician_panel/tracklists";
    }

    @GetMapping("/{id}")
    public String getTraclistPageById(@PathVariable Long id, Model model) {
        model.addAttribute("tracklist",tracklistService.findTracklistById(id));
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("userCreators", userDetailsService.loadNameWithCreatorsByUsername(username));
        model.addAttribute("userTracks", userDetailsService.loadNameWithTracksByUsername(username));
        return "musician_panel/tracklists/tracklist";
    }

    @PostMapping("/{id}")
    public String editTracklistNameById(@PathVariable Long id, @ModelAttribute EditItemNameDto tracklist) {
        tracklist.setItemId(id);
        tracklistService.editTracklistNameById(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d",id);
    }

    @GetMapping("/addNewTracklist")
    public String getCreateNewTracklistPage(Model model) {
        model.addAttribute("tracklist_types", tracklistTypeService.getAllTracklistTypes());
        model.addAttribute("genres", genreService.getAllGenres());
        return "musician_panel/tracklists/addNewTracklist";
    }
    @PostMapping("/addNewTracklist")
    public String addNewTracklist(@ModelAttribute NewTracklistDto tracklist) {
        tracklistService.addNewTracklist(tracklist);
        return "redirect:/musician_panel/tracklists";
    }
    @PostMapping("/delete/{tracklistId}")
    public String addNewTracklist(@PathVariable Long tracklistId) {
        var tracklist = tracklistService.findTracklistById(tracklistId);
        tracklistService.deleteTracklist(tracklist);
        return "redirect:/musician_panel/tracklists";
    }

    @PostMapping("/addTrack/{tracklistId}")
    public String addTrackToTracklistId(@PathVariable Long tracklistId,
                                        @ModelAttribute EditTrackInTracklistDto tracklist) {
        tracklist.setTracklistId(tracklistId);
        tracklist.setTrack(trackService.findTrackById(tracklist.getTrackId()));
        tracklistService.addTrackToTracklist(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d",tracklistId);
    }

    @PostMapping("/delTrack/{tracklistId}")
    public String deleteTrackFromTracklistId(@PathVariable Long tracklistId,
                                        @ModelAttribute EditTrackInTracklistDto tracklist) {
        tracklist.setTracklistId(tracklistId);
        tracklist.setTrack(trackService.findTrackById(tracklist.getTrackId()));
        tracklistService.deleteTrackFromTracklist(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d",tracklistId);
    }

    @PostMapping("/addCreator/{tracklistId}")
    public String addCreatorToTracklistId(@PathVariable Long tracklistId,
                                          @ModelAttribute EditCreatorInItemDto tracklist) {
        tracklist.setItemId(tracklistId);
        tracklist.setCreator(creatorService.getCreatorById(tracklist.getCreatorId()));
        tracklistService.addCreatorToTracklist(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d",tracklistId);
    }

    @PostMapping("/delCreator/{tracklistId}")
    public String deleteCreatorFromTracklistId(@PathVariable Long tracklistId,
                                          @ModelAttribute EditCreatorInItemDto tracklist) {
        tracklist.setItemId(tracklistId);
        tracklist.setCreator(creatorService.getCreatorById(tracklist.getCreatorId()));
        tracklistService.deleteCreatorFromTracklist(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d",tracklistId);
    }

    @PostMapping("/setPublish/{tracklistId}")
    public String setPublishByTracklistId(@PathVariable Long tracklistId, @RequestParam Boolean publish) {
        tracklistService.setPublishTracklistById(tracklistId, publish);
        return String.format("redirect:/musician_panel/tracklists/%d",tracklistId);
    }
}
