package com.ISD.AIMS.controller;

import com.ISD.AIMS.model.LP;
import com.ISD.AIMS.service.LPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lps")
@CrossOrigin(origins = "http://localhost:5173")
public class LPController {

    @Autowired
    private LPService lpService;

    @GetMapping
    public List<LP> getAllLPs() {
        return lpService.getAllLPs();
    }

    @GetMapping("/{id}")
    public Optional<LP> getLPById(@PathVariable Long id) {
        return lpService.getLPById(id);
    }

    @PostMapping
    public LP createLP(@RequestBody LP lp) {
        return lpService.saveLP(lp);
    }

    @PutMapping("/{id}")
    public LP updateLP(@PathVariable Long id, @RequestBody LP updatedLP) {
        Optional<LP> existing = lpService.getLPById(id);
        if (existing.isPresent()) {
            LP lp = existing.get();
            lp.setTitle(updatedLP.getTitle());
            lp.setCategory(updatedLP.getCategory());
            lp.setValue(updatedLP.getValue());
            lp.setPrice(updatedLP.getPrice());
            lp.setBarcode(updatedLP.getBarcode());
            lp.setDescription(updatedLP.getDescription());
            lp.setQuantity(updatedLP.getQuantity());
            lp.setWeight(updatedLP.getWeight());
            lp.setDimensions(updatedLP.getDimensions());

            lp.setArtist(updatedLP.getArtist());
            lp.setRecordLabel(updatedLP.getRecordLabel());
            lp.setTracklist(updatedLP.getTracklist());
            lp.setGenre(updatedLP.getGenre());
            lp.setReleaseDate(updatedLP.getReleaseDate());

            return lpService.saveLP(lp);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLP(@PathVariable Long id) {
        lpService.deleteLP(id);
    }

    @GetMapping("/search/title")
    public List<LP> searchByTitle(@RequestParam String keyword) {
        return lpService.searchByTitle(keyword);
    }

    @GetMapping("/search/artist")
    public List<LP> searchByArtist(@RequestParam String artist) {
        return lpService.searchByArtist(artist);
    }

    @GetMapping("/search/genre")
    public List<LP> searchByGenre(@RequestParam String genre) {
        return lpService.searchByGenre(genre);
    }
}
