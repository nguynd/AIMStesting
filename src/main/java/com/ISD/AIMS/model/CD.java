package com.ISD.AIMS.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cds")
public class CD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Thông tin chung
    private String title;
    private String category;  // ví dụ: "Music"
    private double value;     // Giá trị gốc chưa VAT
    private double price;     // Giá bán chưa VAT

    // Thông tin riêng của CD
    private String artist;
    private String recordLabel;
    private String tracklist;       // Lưu dưới dạng CSV: "Track1, Track2, Track3"
    private String genre;
    private LocalDate releaseDate;

    // Thông tin logistics (vật lý)
    private String barcode;
    private String description;     // e.g., "New", "Used"
    private int quantity;
    private LocalDate warehouseDate;
    private double weight;          // kg
    private String dimensions;      // e.g., "12x12x1 cm"

    // Constructors
    public CD() {}

    public CD(String title, String category, double value, double price,
              String artist, String recordLabel, String tracklist, String genre,
              LocalDate releaseDate, String barcode, String description, int quantity,
              LocalDate warehouseDate, double weight, String dimensions) {
        this.title = title;
        this.category = category;
        this.value = value;
        this.price = price;
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.tracklist = tracklist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.barcode = barcode;
        this.description = description;
        this.quantity = quantity;
        this.warehouseDate = warehouseDate;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    // Getter & Setter đầy đủ (bạn có thể dùng Lombok nếu muốn gọn hơn)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

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

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDate getWarehouseDate() { return warehouseDate; }
    public void setWarehouseDate(LocalDate warehouseDate) { this.warehouseDate = warehouseDate; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getDimensions() { return dimensions; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }
}
