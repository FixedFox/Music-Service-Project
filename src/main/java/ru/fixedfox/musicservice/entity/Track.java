package ru.fixedfox.musicservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long trackId;

    @Column(name = "track_name")
    @NotNull
    private String trackName;

    @Column(name = "count_of_plays")
    @NotNull
    private Long countOfPlays;

    @Column(name = "is_adult_content")
    @NotNull
    private Boolean isAdultContent;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "tracks_creators",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "creators_id"))
    private Set<Creator> creators = new LinkedHashSet<>();

    public Set<Creator> getCreators() {
        return creators;
    }

    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    public Track() {
    }

    public Track(Long trackId, String trackName, Long countOfPlays, Boolean isAdultContent, Genre genre) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.countOfPlays = countOfPlays;
        this.isAdultContent = isAdultContent;
        this.genre = genre;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Long getCountOfPlays() {
        return countOfPlays;
    }

    public void setCountOfPlays(Long countOfPlays) {
        this.countOfPlays = countOfPlays;
    }

    public Boolean getAdultContent() {
        return isAdultContent;
    }

    public void setAdultContent(Boolean adultContent) {
        isAdultContent = adultContent;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
