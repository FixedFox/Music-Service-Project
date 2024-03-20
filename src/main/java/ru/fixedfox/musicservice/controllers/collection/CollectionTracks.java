package ru.fixedfox.musicservice.controllers.collection;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fixedfox.musicservice.dto.EditTrackMyLibraryDto;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

@Controller
@RequestMapping("/my_collection/tracks")
public class CollectionTracks {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public CollectionTracks(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @GetMapping
    public String getMyCollectionTracks(Model model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("tracks", userDetailsServiceImpl.getAllLibraryTracksByUsername(username));
        return "my_collection/tracks";
    }

    @PostMapping("/delete/{trackId}")
    public String deleteTrackFromMyCollection(@PathVariable Long trackId) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        var trackInLibrary = new EditTrackMyLibraryDto();
        trackInLibrary.setTrackId(trackId);
        trackInLibrary.setUsername(username);
        userDetailsServiceImpl.deleteTrackFromMyLibraryById(trackInLibrary);
        return "my_collection/tracks";
    }

}
