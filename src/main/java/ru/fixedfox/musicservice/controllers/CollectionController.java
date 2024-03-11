package ru.fixedfox.musicservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mycollection")
public class CollectionController {

    @GetMapping()
    public String getMyCollection() {
        return "mycollection";
    }
    @GetMapping("/tracks")
    public String getMyCollectionTracks() {
        return "mycollection/tracks";
    }
    @GetMapping("/albums")
    public String getMyCollectionAlbums() {
        return "mycollection/albums";
    }
    @GetMapping("/genres")
    public String getMyCollectionGenres() {
        return "mycollection/genres";
    }
    @GetMapping("/creators")
    public String getMyCollectionCreators() {
        return "mycollection/creators";
    }
}
