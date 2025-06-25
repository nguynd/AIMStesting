package com.ISD.AIMS.repository;

import com.ISD.AIMS.model.DVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DVDRepository extends JpaRepository<DVD, Long> {
    List<DVD> findByTitleContainingIgnoreCase(String keyword);
    List<DVD> findByDirectorContainingIgnoreCase(String director);
    List<DVD> findByGenre(String genre);
}
