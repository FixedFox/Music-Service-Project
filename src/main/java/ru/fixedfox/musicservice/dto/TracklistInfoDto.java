package ru.fixedfox.musicservice.dto;

import java.util.Set;

public class TracklistInfoDto {
    String tracklistName;
    Set<String> tracklistCreators;
    String tracklistGenre;
    String tracklistType;

    public TracklistInfoDto() {
    }

    public String getTracklistName() {
        return tracklistName;
    }

    public void setTracklistName(String tracklistName) {
        this.tracklistName = tracklistName;
    }

    public Set<String> getTracklistCreators() {
        return tracklistCreators;
    }

    public void setTracklistCreators(Set<String> tracklistCreators) {
        this.tracklistCreators = tracklistCreators;
    }

    public String getTracklistGenre() {
        return tracklistGenre;
    }

    public void setTracklistGenre(String tracklistGenre) {
        this.tracklistGenre = tracklistGenre;
    }

    public String getTracklistType() {
        return tracklistType;
    }

    public void setTracklistType(String tracklistType) {
        this.tracklistType = tracklistType;
    }


}
