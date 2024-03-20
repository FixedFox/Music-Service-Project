package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fixedfox.musicservice.entity.Creator;

import java.util.Optional;
import java.util.Set;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    Set<Creator> findByUser_Id(Long userId);

    Optional<Creator> findById(Long creatorId);

    @Query(nativeQuery = true, value = "SELECT * FROM creators t1 " +
            "JOIN creators_tracks t2 ON t2.creator_id = t1.id " +
            "JOIN tracks t3 ON t3.id = t2.track_id " +
            "JOIN librarys t4 ON t4.track_id = t3.id " +
            "WHERE t4.user_id = :userId")
    Set<Creator> getCreatorsByLibraryUserId(Long userId);

@Query(nativeQuery = true, value = "SELECT DISTINCT(t1.id), t1.creator_name, t1.user_id FROM creators t1\n" +
        "LEFT JOIN subscriptions t2 ON t2.creator_id = t1.id\n" +
        "WHERE ((t2.user_id <> :userId) OR (t2.user_id IS NULL)) AND (t1.creator_name ILIKE :name)")
    Set<Creator> findByCreatorNameByUserId(String name, Long userId);
}
