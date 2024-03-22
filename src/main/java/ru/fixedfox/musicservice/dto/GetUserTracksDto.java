package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Track;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GetUserTracksDto {
    private String name;
    private Set<Track> tracks;

    public GetUserTracksDto() {
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
        this.tracks =this.tracks.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
