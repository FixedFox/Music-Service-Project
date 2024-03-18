package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Track;

import java.util.Set;

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
    }
}
