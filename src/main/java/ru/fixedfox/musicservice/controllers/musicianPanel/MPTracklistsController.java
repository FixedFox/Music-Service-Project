package ru.fixedfox.musicservice.controllers.musicianPanel;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TracklistService tracklistService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private TracklistTypeService tracklistTypeService;
    @Autowired
    private CreatorService creatorService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private TelegramIntegrationService telegramIntegrationService;


    @GetMapping
    public String getAllTracklistsPage(Model model) {
        model.addAttribute("tracklists", tracklistService.findAllTracklists());
        return "musician_panel/tracklists";
    }

    @GetMapping("/{id}")
    public String getTraclistPageById(@PathVariable Long id, Model model) {
        model.addAttribute("tracklist", tracklistService.findTracklistById(id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userCreators",
                creatorService.findCreatorsByUserIdIsNotInTracklistById(id, user.getId()));
        model.addAttribute("userTracks",
                trackService.findTracksByUserIdIsNotInTracklistById(id ,user.getId()));
        return "musician_panel/tracklists/tracklist";
    }

    @PostMapping("/{id}")
    public String editTracklistNameById(@PathVariable Long id, @ModelAttribute EditItemNameDto tracklist) {
        tracklist.setItemId(id);
        tracklistService.editTracklistNameById(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d", id);
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
        return String.format("redirect:/musician_panel/tracklists/%d", tracklistId);
    }

    @PostMapping("/delTrack/{tracklistId}")
    public String deleteTrackFromTracklistId(@PathVariable Long tracklistId,
                                             @ModelAttribute EditTrackInTracklistDto tracklist) {
        tracklist.setTracklistId(tracklistId);
        tracklist.setTrack(trackService.findTrackById(tracklist.getTrackId()));
        tracklistService.deleteTrackFromTracklist(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d", tracklistId);
    }

    @PostMapping("/addCreator/{tracklistId}")
    public String addCreatorToTracklistId(@PathVariable Long tracklistId,
                                          @ModelAttribute EditCreatorInItemDto tracklist) {
        tracklist.setItemId(tracklistId);
        tracklist.setCreator(creatorService.getCreatorById(tracklist.getCreatorId()));
        tracklistService.addCreatorToTracklist(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d", tracklistId);
    }

    @PostMapping("/delCreator/{tracklistId}")
    public String deleteCreatorFromTracklistId(@PathVariable Long tracklistId,
                                               @ModelAttribute EditCreatorInItemDto tracklist) {
        tracklist.setItemId(tracklistId);
        tracklist.setCreator(creatorService.getCreatorById(tracklist.getCreatorId()));
        tracklistService.deleteCreatorFromTracklist(tracklist);
        return String.format("redirect:/musician_panel/tracklists/%d", tracklistId);
    }

    @PostMapping("/setPublish/{tracklistId}")
    public String setPublishByTracklistId(@PathVariable Long tracklistId, @RequestParam Boolean publish) {
        tracklistService.setPublishTracklistById(tracklistId, publish);
        if (publish) {
            var tracklist = tracklistService.getInformationAboutTracklistForNotify(tracklistId);
            var usersForSend = userDetailsService.findUserBySubscriptionByTracklist(tracklistId);
            telegramIntegrationService.NotificationNewTracklistPublished(usersForSend, tracklist);
        }
        return String.format("redirect:/musician_panel/tracklists/%d", tracklistId);
    }
}
