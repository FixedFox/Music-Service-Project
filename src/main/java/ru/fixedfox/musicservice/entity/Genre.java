package ru.fixedfox.musicservice.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer genreId;

    @Column(name = "genre_name")
    @Size(min = 3)
    private String genreName;

    public Genre() {
    }

    public Genre(Integer genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
