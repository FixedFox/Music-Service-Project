package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.TracklistType;

public interface TracklistTypeRepository extends JpaRepository<TracklistType, Integer> {

    TracklistType getTracklistTypeByTracklistTypeName(String name);
}
