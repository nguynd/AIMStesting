package com.ISD.AIMS.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cds")
public class CD extends Product {

    // Thuộc tính riêng của CD
    private String artist;
    private String recordLabel;
    private String tracklist;    // "Track1, Track2, Track3"
    private String genre;
    private LocalDate releaseDate;
    private LocalDate warehouseDate;

    // Constructors
    public CD() {}

    public CD(String title, String category, double value, double price,
              String barcode, String description, int quantity, double weight, String dimensions,
              String artist, String recordLabel, String tracklist, String genre,
              LocalDate releaseDate, LocalDate warehouseDate) {

        // Sử dụng setter của lớp cha (Product)
        setTitle(title);
        setCategory(category);
        setValue(value);
        setPrice(price);
        setBarcode(barcode);
        setDescription(description);
        setQuantity(quantity);
        setWeight(weight);
        setDimensions(dimensions);

        // Gán trường riêng
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.tracklist = tracklist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.warehouseDate = warehouseDate;
    }

    // Getter & Setter cho các trường riêng

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getWarehouseDate() {
        return warehouseDate;
    }

    public void setWarehouseDate(LocalDate warehouseDate) {
        this.warehouseDate = warehouseDate;
    }
}
