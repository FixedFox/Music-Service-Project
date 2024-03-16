package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.entity.Track;
import ru.fixedfox.musicservice.repository.TrackRepository;

import java.util.List;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track getTrackById(Long id) {
        return trackRepository.getReferenceById(id);
    }

    public List<Track> getAllTracksOfCreator() {
        return trackRepository.findAll();
    }

    public void addNewTrack(Track track) {
        trackRepository.save(track);
    }
}
