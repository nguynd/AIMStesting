package com.ISD.AIMS;

import com.ISD.AIMS.model.Book;
import com.ISD.AIMS.model.CD;
import com.ISD.AIMS.repository.BookRepository;
import com.ISD.AIMS.repository.CDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CDRepository cdRepository;

    @Override
    public void run(String... args) throws Exception {
        // Chỉ thêm dữ liệu nếu kho lưu trữ sách trống
        if (bookRepository.count() == 0) {
            System.out.println("No data found. Seeding initial data...");

            // --- Tạo Sách (Book) ---
            Book book1 = new Book();
            book1.setTitle("Nhà Giả Kim");
            book1.setCategory("Tiểu thuyết");
            book1.setPrice(109000);
            book1.setQuantity(50);
            book1.setAuthors("Paulo Coelho");
            book1.setGenre("Phiêu lưu");
            // URL ảnh đã được sửa
            book1.setImageUrl("https://salt.tikicdn.com/cache/w1200/ts/product/d1/1a/35/9168953181585034a740702fba7c92d5.jpg");
            bookRepository.save(book1);

            Book book2 = new Book();
            book2.setTitle("Đắc Nhân Tâm");
            book2.setCategory("Sách kỹ năng");
            book2.setPrice(120000);
            book2.setQuantity(100);
            book2.setAuthors("Dale Carnegie");
            book2.setGenre("Self-help");
            // URL ảnh đã được sửa
            book2.setImageUrl("https://salt.tikicdn.com/cache/w1200/ts/product/55/63/80/c854734340d8299d940b5f0535384218.jpg");
            bookRepository.save(book2);


            // --- Tạo CD ---
            CD cd1 = new CD();
            cd1.setTitle("ABBA Gold");
            cd1.setCategory("Âm nhạc");
            cd1.setPrice(250000);
            cd1.setQuantity(20);
            cd1.setArtist("ABBA");
            cd1.setGenre("Pop");
            cd1.setReleaseDate(LocalDate.of(1992, 9, 21));
            // URL ảnh đã được sửa
            cd1.setImageUrl("https://m.media-amazon.com/images/I/71tb1s3gV2L._UF1000,1000_QL80_.jpg");
            cdRepository.save(cd1);


            System.out.println("Initial data seeded.");
        }
    }
}