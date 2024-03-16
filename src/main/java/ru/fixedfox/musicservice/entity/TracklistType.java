package ru.fixedfox.musicservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "tracklist_types")
public class TracklistType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tracklist_type_name")
    private String tracklistTypeName;

    public TracklistType() {
    }

    public TracklistType(Integer id, String tracklistTypeName) {
        this.id = id;
        this.tracklistTypeName = tracklistTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTracklistTypeName() {
        return tracklistTypeName;
    }

    public void setTracklistTypeName(String tracklistTypeName) {
        this.tracklistTypeName = tracklistTypeName;
    }
}