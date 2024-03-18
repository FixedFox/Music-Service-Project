package ru.fixedfox.musicservice.dto;

import ru.fixedfox.musicservice.entity.Track;

public class EditTrackInTracklistDto {
    Long tracklistId;
    Long trackId;
    Track track;

    public EditTrackInTracklistDto() {
    }

    public Long getTracklistId() {
        return tracklistId;
    }

    public void setTracklistId(Long tracklistId) {
        this.tracklistId = tracklistId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
