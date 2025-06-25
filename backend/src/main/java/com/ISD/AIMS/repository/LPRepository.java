package com.ISD.AIMS.repository;

import com.ISD.AIMS.model.LP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LPRepository extends JpaRepository<LP, Long> {
    List<LP> findByTitleContainingIgnoreCase(String keyword);
    List<LP> findByArtistContainingIgnoreCase(String artist);
    List<LP> findByGenre(String genre);
}
