package ru.fixedfox.musicservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false, columnDefinition = "<empty>")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))
    private Set<Authority> authorities = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    private Set<Creator> creators = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Track> tracks = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "librarys",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Set<Track> librarytracks = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "creator_id"))
    private Set<Creator> subscribtions = new LinkedHashSet<>();

    @Column(name = "telegram_nickname")
    private String telegramNickname;

    @Column(name = "telegram_id")
    private Long telegram_id;

    public Long getTelegramid() {
        return telegram_id;
    }

    public void setTelegram_id(Long telegram_id) {
        this.telegram_id = telegram_id;
    }

    public String getTelegramNickname() {
        return telegramNickname;
    }

    public void setTelegramNickname(String telegramNickname) {
        this.telegramNickname = telegramNickname;
    }

    public Set<Creator> getSubscribtions() {
        return subscribtions;
    }

    public void setSubscribtions(Set<Creator> subscribtions) {
        this.subscribtions = subscribtions;
    }

    public void addSubscription(Creator creator) {
        subscribtions.add(creator);
    }

    public void removeSubscription(Creator creator) {
        subscribtions.remove(creator);
    }

    public Set<Track> getLibrarytracks() {
        return librarytracks;
    }

    public void setLibrarytracks(Set<Track> librarytracks) {
        this.librarytracks = librarytracks;
    }

    public void addLibrarytrack(Track track) {
        librarytracks.add(track);
    }

    public void removeLibrarytrack(Track track) {
        librarytracks.remove(track);
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public Set<Creator> getCreators() {
        return creators;
    }

    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAuthorityName(String authority) {
        return authorities.stream().map(Authority::getAuthority).anyMatch(i -> i.equals(authority));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User() {
    }
}
