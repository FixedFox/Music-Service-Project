package ru.fixedfox.musicservice.services;

import org.springframework.stereotype.Service;
import ru.fixedfox.musicservice.entity.Genre;
import ru.fixedfox.musicservice.repository.GenreRepository;

import javax.persistence.criteria.CriteriaBuilder;
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

    public Genre getGenreByName(String genreName) {
        return genreRepository.findByGenreName(genreName);
    }

    public void createNewGenre(Genre genre) {
        genreRepository.save(genre);
    }


    public void deleteById(Integer id) {
        genreRepository.deleteById(id);
    }
}
