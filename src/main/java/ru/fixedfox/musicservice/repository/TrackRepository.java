package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fixedfox.musicservice.entity.Track;

import java.util.Set;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track findByTrackId(Long id);

    @Query(nativeQuery = true, value = "SELECT t1.id, t1.track_name, t1.count_of_plays, t1.is_adult_content," +
            " t1.genre_id, t1.user_id FROM tracks t1 " +
            "JOIN tracks_tracklists t2 ON t2.track_id = t1.id " +
            "JOIN tracklists t3 ON t3.id = t2.tracklist_id " +
            "JOIN creators_tracks t4 ON t4.track_id = t1.id " +
            "JOIN creators t5 ON t5.id = t4.creator_id " +
            "LEFT JOIN librarys t6 ON t6.track_id = t1.id " +
            "WHERE ((t1.track_name ILIKE :name ) OR (t3.name ILIKE :name) OR (t5.creator_name ILIKE :name)) AND " +
            "((t6.user_id is null) OR (t6.user_id <> :userId)) AND (t3.published = TRUE)" +
            "ORDER BY count_of_plays DESC")
    Set<Track> findAllByTrackTracklistCreatorNameWithUserId(String name, Long userId);

    @Query(nativeQuery = true, value = "SELECT id, track_name, count_of_plays, is_adult_content, genre_id, user_id FROM tracks\n" +
            "WHERE (user_id = :userId ) AND id NOT IN\n" +
            "(SELECT t1.id FROM tracks t1\n" +
            "JOIN tracks_tracklists t2 ON t2.track_id = t1.id\n" +
            "WHERE t2.tracklist_id = :tracklistId)")
    Set<Track> findTracksByUserIdIsNotInTracklistById(Long tracklistId, Long userId);
}
