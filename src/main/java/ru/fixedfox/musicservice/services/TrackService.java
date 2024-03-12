package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.entity.Track;
import ru.fixedfox.musicservice.repository.TrackRepository;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track getTrackById(Long id) {
        return trackRepository.getReferenceById(id);
    }
}
