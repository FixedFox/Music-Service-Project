package ru.fixedfox.musicservice.dto;

public class EditTrackMyLibraryDto {
    Long trackId;
    String username;

    public EditTrackMyLibraryDto() {
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
