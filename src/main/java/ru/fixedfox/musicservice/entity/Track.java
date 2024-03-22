package ru.fixedfox.musicservice.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tracks")
public class Track implements Comparable<Track> {

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

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany
    @JoinTable(name = "creators_tracks",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id"))
    private Set<Creator> creators = new LinkedHashSet<>();


    @ManyToMany(mappedBy = "tracks")
    private Set<Tracklist> tracklists = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(mappedBy = "librarytracks")
    private Set<User> users = new LinkedHashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tracklist> getTracklists() {
        return tracklists;
    }

    public void setTracklists(Set<Tracklist> tracklists) {
        this.tracklists = tracklists;
    }


    public Set<Creator> getCreators() {
        return creators;
    }
    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    public void addCreator(Creator creator) {
        creators.add(creator);
    }
    public void removeCreator(Creator creator) {
        creators.remove(creator);
    }
    public Track() {
    }

    public Track(Long trackId, String trackName, Long countOfPlays, Boolean isAdultContent, Genre genre) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.countOfPlays = countOfPlays;
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


    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Track track = (Track) o;
        return trackId != null && Objects.equals(trackId, track.trackId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public int compareTo(Track o) {
        return this.trackName.compareTo(o.trackName);
    }
}
