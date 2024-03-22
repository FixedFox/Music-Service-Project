package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fixedfox.musicservice.entity.Genre;

import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByGenreName(String name);

    @Query(nativeQuery = true, value = "SELECT t1.id, t1.genre_name FROM genres t1 " +
            "JOIN tracks t2 ON t2.genre_id = t1.id " +
            "JOIN librarys t3 ON t3.track_id = t2.id " +
            "WHERE t3.user_id = :id" )
    Set<Genre> getGenresByLibraryUserId(Long id);
}
