package ru.fixedfox.musicservice.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name = "genre_id")
    private Integer genreId;

    @Column(name = "genre_name")
    @NotNull
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
