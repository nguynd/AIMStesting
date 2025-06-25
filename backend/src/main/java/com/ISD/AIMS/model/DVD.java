package com.ISD.AIMS.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dvds")
public class DVD extends Product {

    private String discType;       // "Blu-ray", "HD-DVD"
    private String director;
    private int runtime;           // Phút
    private String studio;
    private String language;
    private String subtitles;
    private String genre;
    private LocalDate releaseDate;

    public DVD() {}

    // Getters & Setters

    public String getDiscType() { return discType; }
    public void setDiscType(String discType) { this.discType = discType; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getRuntime() { return runtime; }
    public void setRuntime(int runtime) { this.runtime = runtime; }

    public String getStudio() { return studio; }
    public void setStudio(String studio) { this.studio = studio; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getSubtitles() { return subtitles; }
    public void setSubtitles(String subtitles) { this.subtitles = subtitles; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
}
