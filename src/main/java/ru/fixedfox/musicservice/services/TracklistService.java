package ru.fixedfox.musicservice.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.dto.*;
import ru.fixedfox.musicservice.entity.Creator;
import ru.fixedfox.musicservice.entity.Tracklist;
import ru.fixedfox.musicservice.repository.TracklistRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Tracklist> findAllTracklists() {
        return tracklistRepository.findAll();
    }

    public Set<Tracklist> findTracklistsByName(String tracklistName) {
        return tracklistRepository.findByNameContainingIgnoreCase(tracklistName);
    }

    public Tracklist findTracklistById(Long id) {
        return tracklistRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Tracklist with id = '%s'", id)));
    }

    public void addNewTracklist(NewTracklistDto tracklist) {
        var tracklistForBase = new Tracklist();
        tracklistForBase.setName(tracklist.getName());
        tracklistForBase.setTracklistType(
                tracklistTypeService.getTraclistTypeByName(
                        tracklist.getTracklist_type()));
        tracklistForBase.setGenre(
                genreService.getGenreByName(tracklist.getGenre()));
        tracklistForBase.setAddTime(LocalDateTime.now());
        tracklistRepository.save(tracklistForBase);
    }

    public void deleteTracklist(Tracklist tracklist) {
        tracklistRepository.delete(tracklist);
    }

    public void addTrackToTracklist(EditTrackInTracklistDto tracklist) {
        var tracklistFromBase = findTracklistById(tracklist.getTracklistId());
        tracklistFromBase.addTrack(tracklist.getTrack());
        tracklistRepository.save(tracklistFromBase);
    }

    public void deleteTrackFromTracklist(EditTrackInTracklistDto tracklist) {
        var tracklistFromBase = findTracklistById(tracklist.getTracklistId());
        tracklistFromBase.removeTrack(tracklist.getTrack());
        tracklistRepository.save(tracklistFromBase);
    }

    public void addCreatorToTracklist(EditCreatorInItemDto tracklist) {
        var tracklistFromBase = findTracklistById(tracklist.getItemId());
        tracklistFromBase.addCreator(tracklist.getCreator());
        tracklistRepository.save(tracklistFromBase);
    }

    public void deleteCreatorFromTracklist(EditCreatorInItemDto tracklist) {
        var tracklistFromBase = findTracklistById(tracklist.getItemId());
        tracklistFromBase.removeCreator(tracklist.getCreator());
        tracklistRepository.save(tracklistFromBase);
    }

    @Transactional
    public void editTracklistNameById(EditItemNameDto tracklist) {
        var tracklistFromBase = findTracklistById(tracklist.getItemId());
        tracklistFromBase.setName(tracklist.getItemName());
        tracklistRepository.save(tracklistFromBase);
    }

    public Set<Tracklist> findTracklistsByUserLibrary(Long userId) {
        return tracklistRepository.findTracklistsByLibraryUserId(userId);
    }

    @Transactional
    public void setPublishTracklistById(Long tracklistId, Boolean publish) {
        var tracklistFromBase = tracklistRepository.findById(tracklistId).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Tracklist with id = '%s'", tracklistId)));
        tracklistFromBase.setPublished(publish);
        tracklistRepository.save(tracklistFromBase);
    }

    public TracklistInfoDto getInformationAboutTracklistForNotify(Long tracklistId) {
        var tracklist = new TracklistInfoDto();
        var tracklistFromBase = tracklistRepository.findById(tracklistId).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Tracklist with id = '%s'", tracklistId)));
        tracklist.setTracklistName(tracklistFromBase.getName());
        tracklist.setTracklistGenre(tracklistFromBase.getGenre().getGenreName());
        tracklist.setTracklistCreators(tracklistFromBase.getCreators().stream().map(Creator::getCreatorName).collect(Collectors.toSet()));
        tracklist.setTracklistType(tracklistFromBase.getTracklistType().getTracklistTypeName());
        return tracklist;
    }
}
