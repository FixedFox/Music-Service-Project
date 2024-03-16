package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.entity.Tracklist;
import ru.fixedfox.musicservice.repository.TracklistRepository;

import java.util.List;

@Service
public class TracklistService {

    private final TracklistRepository tracklistRepository;
    private final GenreService genreService;
    private final TracklistTypeService tracklistTypeService;

    public TracklistService(TracklistRepository tracklistRepository, GenreService genreService, TracklistTypeService tracklistTypeService) {
        this.tracklistRepository = tracklistRepository;
        this.genreService = genreService;
        this.tracklistTypeService = tracklistTypeService;
    }

    public List<Tracklist> getAllTracklists() {
        return tracklistRepository.findAll();
    }

//    public void addNewTracklist(TracklistCreateDto tracklist) {
//        var newTracklist = new Tracklist();
//        newTracklist.setName(tracklist.getName());
//        newTracklist.setTracklistType(tracklistTypeService.getTraclistTypeByName(tracklist.getTracklist_type()));
//        newTracklist.setGenre(genreService.getGenreByName(tracklist.getGenre()));
//        newTracklist.setAddTime(LocalDateTime.now());
//        tracklistRepository.save(newTracklist);
//    }

}
