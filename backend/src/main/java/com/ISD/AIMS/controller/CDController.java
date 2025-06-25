package com.ISD.AIMS.controller;

import com.ISD.AIMS.model.CD;
import com.ISD.AIMS.service.CDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cds")
@CrossOrigin(origins = "http://localhost:5173") // Cho phép frontend gọi API (React/Vite)
public class CDController {

    @Autowired
    private CDService cdService;

    // ✅ GET tất cả CD
    @GetMapping
    public List<CD> getAllCDs() {
        return cdService.getAllCDs();
    }

    // ✅ GET 1 CD theo ID
    @GetMapping("/{id}")
    public Optional<CD> getCDById(@PathVariable Long id) {
        return cdService.getCDById(id);
    }

    // ✅ POST: Thêm mới CD
    @PostMapping
    public CD createCD(@RequestBody CD cd) {
        return cdService.saveCD(cd);
    }

    // ✅ PUT: Cập nhật CD
    @PutMapping("/{id}")
    public CD updateCD(@PathVariable Long id, @RequestBody CD updatedCD) {
        Optional<CD> existing = cdService.getCDById(id);
        if (existing.isPresent()) {
            CD cd = existing.get();
            // Cập nhật các trường
            cd.setTitle(updatedCD.getTitle());
            cd.setCategory(updatedCD.getCategory());
            cd.setValue(updatedCD.getValue());
            cd.setPrice(updatedCD.getPrice());
            cd.setArtist(updatedCD.getArtist());
            cd.setRecordLabel(updatedCD.getRecordLabel());
            cd.setTracklist(updatedCD.getTracklist());
            cd.setGenre(updatedCD.getGenre());
            cd.setReleaseDate(updatedCD.getReleaseDate());
            cd.setBarcode(updatedCD.getBarcode());
            cd.setDescription(updatedCD.getDescription());
            cd.setQuantity(updatedCD.getQuantity());
            cd.setWarehouseDate(updatedCD.getWarehouseDate());
            cd.setWeight(updatedCD.getWeight());
            cd.setDimensions(updatedCD.getDimensions());
            cd.setImageUrl(updatedCD.getImageUrl());
            return cdService.saveCD(cd);
        } else {
            return null;
        }
    }

    // ✅ DELETE: Xóa CD
    @DeleteMapping("/{id}")
    public void deleteCD(@PathVariable Long id) {
        cdService.deleteCD(id);
    }

    // ✅ Tìm theo tiêu đề
    @GetMapping("/search/title")
    public List<CD> searchByTitle(@RequestParam String keyword) {
        return cdService.searchByTitle(keyword);
    }

    // ✅ Tìm theo nghệ sĩ
    @GetMapping("/search/artist")
    public List<CD> searchByArtist(@RequestParam String artist) {
        return cdService.searchByArtist(artist);
    }

    // ✅ Tìm theo thể loại
    @GetMapping("/search/genre")
    public List<CD> searchByGenre(@RequestParam String genre) {
        return cdService.searchByGenre(genre);
    }
}
