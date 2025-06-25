package com.ISD.AIMS.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lps")
public class LP extends Product {

    private String artist;
    private String recordLabel;
    private String tracklist;
    private String genre;
    private LocalDate releaseDate;

    public LP() {}

    // Getters & Setters

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getRecordLabel() { return recordLabel; }
    public void setRecordLabel(String recordLabel) { this.recordLabel = recordLabel; }

    public String getTracklist() { return tracklist; }
    public void setTracklist(String tracklist) { this.tracklist = tracklist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
}
