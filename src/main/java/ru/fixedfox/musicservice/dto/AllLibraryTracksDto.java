package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Track;

import java.util.Set;

public class AllLibraryTracksDto {
    Set<Track> tracks;

    public AllLibraryTracksDto() {
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }
}
