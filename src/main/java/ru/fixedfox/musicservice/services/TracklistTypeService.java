package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.entity.Tracklist;
import ru.fixedfox.musicservice.entity.TracklistType;
import ru.fixedfox.musicservice.repository.TracklistTypeRepository;

import java.util.List;

@Service
public class TracklistTypeService {

    private final TracklistTypeRepository tracklistTypeRepository;

    public TracklistTypeService(TracklistTypeRepository tracklistTypeRepository) {
        this.tracklistTypeRepository = tracklistTypeRepository;
    }

    public List<TracklistType> getAllTracklistTypes() {
        return tracklistTypeRepository.findAll();
    }

    public TracklistType getTraclistTypeByName(String tracklistTypeName) {
        return tracklistTypeRepository.getTracklistTypeByTracklistTypeName(tracklistTypeName);
    }
}
