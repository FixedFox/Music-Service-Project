package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fixedfox.musicservice.entity.Creator;

import java.util.Optional;
import java.util.Set;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    public Set<Creator> findByCreatorNameContainingIgnoreCase(String name);

    public Set<Creator> findByUser_Id(Long userId);

    public Optional<Creator> findById(Long creatorId);

    @Query(nativeQuery = true, value = "SELECT * FROM creators t1 " +
            "JOIN creators_tracks t2 ON t2.creator_id = t1.id " +
            "JOIN tracks t3 ON t3.id = t2.track_id " +
            "JOIN librarys t4 ON t4.track_id = t3.id " +
            "WHERE t4.user_id = :userId")
    Set<Creator> getCreatorsByLibraryUserId(Long userId);
}
