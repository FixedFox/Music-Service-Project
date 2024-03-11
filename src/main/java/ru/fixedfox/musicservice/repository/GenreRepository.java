package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fixedfox.musicservice.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
