package ru.fixedfox.musicservice.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tracklists")
public class Tracklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tracklist_type_id", nullable = false)
    private TracklistType tracklistType;

    @ManyToMany
    private Set<Track> tracks = new LinkedHashSet<>();


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "add_time", nullable = false)
    private LocalDateTime addTime;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany
    private Set<Creator> creators = new LinkedHashSet<>();

    public Set<Creator> getCreators() {
        return creators;
    }

    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public TracklistType getTracklistType() {
        return tracklistType;
    }

    public void setTracklistType(TracklistType tracklistType) {
        this.tracklistType = tracklistType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tracklist tracklist = (Tracklist) o;
        return id != null && Objects.equals(id, tracklist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
