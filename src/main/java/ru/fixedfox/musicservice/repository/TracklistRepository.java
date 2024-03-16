package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.Tracklist;

public interface TracklistRepository extends JpaRepository<Tracklist, Long> {
}
