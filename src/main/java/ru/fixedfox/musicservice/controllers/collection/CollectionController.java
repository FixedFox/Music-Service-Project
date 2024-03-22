package ru.fixedfox.musicservice.controllers.collection;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fixedfox.musicservice.entity.Track;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.*;

@Controller
@RequestMapping("/my_collection")
public class CollectionController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final TracklistService tracklistService;
    private final GenreService genreService;
    private final CreatorService creatorService;

    private final TrackService trackService;

    public CollectionController(UserDetailsServiceImpl userDetailsServiceImpl,
                                TracklistService tracklistService,
                                GenreService genreService,
                                CreatorService creatorService,
                                TrackService trackService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.tracklistService = tracklistService;
        this.genreService = genreService;
        this.creatorService = creatorService;
        this.trackService = trackService;
    }

    @GetMapping()
    public String getMyCollection(Model model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("user", userDetailsServiceImpl.getUserForMainPageByUsename(username));
        return "my_collection";
    }

    @GetMapping("/tracklists")
    public String getMyCollectionAlbums(Model model) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("tracklists", tracklistService.findTracklistsByUserLibrary(userId));
        return "my_collection/tracklists";
    }

    @GetMapping("/tracklists/{tracklistId}")
    public String getMyCollectionTracksByAlbumId (@PathVariable Long tracklistId, Model model) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("tracklist", tracklistService.findTracklistById(tracklistId));
        model.addAttribute("tracks", trackService.findTracksByUserIdByAlbumId(tracklistId, userId));
        return "/my_collection/tracklists/tracklist";
    }

    @GetMapping("/genres")
    public String getMyCollectionGenres(Model model) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("genres", genreService.getGenresByLibraryUserId(userId) );
        return "my_collection/genres";
    }

    @GetMapping("/creators")
    public String getMyCollectionCreators(Model model) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("creators",creatorService.findCreatorByLibraryUserId(userId));
        return "my_collection/creators";
    }
}
