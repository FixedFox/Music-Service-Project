package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.entity.Genre;
import ru.fixedfox.musicservice.repository.GenreRepository;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres(){;
        return  genreRepository.findAll();
    }

    public void createNewGenre(Genre genre) {
        genreRepository.save(genre);
    }
}
