package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.Tracklist;

import java.util.Optional;
import java.util.Set;

public interface TracklistRepository extends JpaRepository<Tracklist, Long> {
    public Set<Tracklist> findByNameContainingIgnoreCase(String tracklistName);
    public Optional<Tracklist> findById(Long id);
}
