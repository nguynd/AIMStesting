package com.ISD.AIMS.repository;

import com.ISD.AIMS.model.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CDRepository extends JpaRepository<CD, Long> {

    // Tìm CD theo tiêu đề chứa từ khóa (bỏ qua hoa thường)
    List<CD> findByTitleContainingIgnoreCase(String keyword);

    // Tìm CD theo nghệ sĩ
    List<CD> findByArtistContainingIgnoreCase(String artist);

    // Tìm CD theo thể loại
    List<CD> findByGenre(String genre);
}
