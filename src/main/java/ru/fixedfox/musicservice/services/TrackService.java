package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.dto.EditCreatorInItemDto;
import ru.fixedfox.musicservice.dto.EditItemNameDto;
import ru.fixedfox.musicservice.entity.Track;
import ru.fixedfox.musicservice.repository.TrackRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track findTrackById(Long id) {
        return trackRepository.findByTrackId(id);
    }

    public List<Track> getAllTracksOfCreator() {
        return trackRepository.findAll();
    }

    public void addNewTrack(Track track) {
        trackRepository.save(track);
    }

    public Set<Track> findTracksByName(String name) {
        return trackRepository.findByTrackNameContainingIgnoreCase(name);
    }

    public void addCreatorToTrack(EditCreatorInItemDto track) {
        var trackFromBase = findTrackById(track.getItemId());
        trackFromBase.addCreator(track.getCreator());
        trackRepository.save(trackFromBase);
    }

    @Transactional
    public void deleteTrack(Track track) {
        trackRepository.delete(track);
    }

    @Transactional
    public void editTrackNameById(EditItemNameDto track) {
        var trackFromBase = findTrackById(track.getItemId());
        trackFromBase.setTrackName(track.getItemName());
        trackRepository.save(trackFromBase);
    }
}
