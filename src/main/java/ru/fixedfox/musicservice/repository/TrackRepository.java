package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fixedfox.musicservice.entity.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
