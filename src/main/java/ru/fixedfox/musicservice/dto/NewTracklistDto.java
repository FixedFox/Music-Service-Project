package ru.fixedfox.musicservice.dto;

public class NewTracklistDto {
    private String name;
    private String tracklist_type;
    private String genre;

    public NewTracklistDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTracklist_type() {
        return tracklist_type;
    }

    public void setTracklist_type(String tracklist_type) {
        this.tracklist_type = tracklist_type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
