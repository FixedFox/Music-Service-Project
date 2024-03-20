package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fixedfox.musicservice.entity.Tracklist;

import java.util.Optional;
import java.util.Set;

public interface TracklistRepository extends JpaRepository<Tracklist, Long> {
    public Set<Tracklist> findByNameContainingIgnoreCase(String tracklistName);
    public Optional<Tracklist> findById(Long id);

    @Query(nativeQuery = true, value = "SELECT t1.id, t1.tracklist_type_id, t1.name, t1.genre_id, t1.add_time from tracklists t1 " +
            "JOIN tracks_tracklists t2 ON t2.tracklist_id = t1.id " +
            "JOIN librarys t3 ON t3.track_id = t2.track_id " +
            "WHERE t3.user_id = :id")
    public Set<Tracklist> findTracklistsByLibraryUserId(@Param("id") Long id);
}
