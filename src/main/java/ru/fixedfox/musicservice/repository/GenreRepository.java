package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixedfox.musicservice.entity.Genre;


public interface GenreRepository extends JpaRepository<Genre, Integer> {
    public Genre findByGenreName(String name);
}
