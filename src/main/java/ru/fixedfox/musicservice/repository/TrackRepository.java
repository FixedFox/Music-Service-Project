package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.Track;
import ru.fixedfox.musicservice.entity.Tracklist;

import javax.persistence.Table;
import java.util.Set;

public interface TrackRepository extends JpaRepository<Track, Long> {
    public Set<Track> findByTrackNameContainingIgnoreCase(String name);
    public Track findByTrackId(Long id);
}
