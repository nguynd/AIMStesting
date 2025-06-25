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
            // URL ảnh đang hoạt động
            book1.setImageUrl("https://cdn0.fahasa.com/media/catalog/product/i/m/image_195509_1_36793.jpg");
            bookRepository.save(book1);

            Book book2 = new Book();
            book2.setTitle("Đắc Nhân Tâm");
            book2.setCategory("Sách kỹ năng");
            book2.setPrice(120000);
            book2.setQuantity(100);
            book2.setAuthors("Dale Carnegie");
            book2.setGenre("Self-help");
            // URL ảnh mới, đã kiểm tra
            book2.setImageUrl("https://bizweb.dktcdn.net/100/418/683/products/dac-nhan-tam-biamem-2019-2-h500.jpg");
            bookRepository.save(book2);


            // --- Tạo CD ---
            CD cd1 = new CD();
            cd1.setTitle("Vàng ABBA");
            cd1.setCategory("Âm nhạc");
            cd1.setPrice(250000);
            cd1.setQuantity(20);
            cd1.setArtist("ABBA");
            cd1.setGenre("Pop");
            cd1.setReleaseDate(LocalDate.of(1992, 9, 21));
            // URL ảnh mới, đã kiểm tra
            cd1.setImageUrl("https://upload.wikimedia.org/wikipedia/en/3/3c/Abba_gold.png");
            cdRepository.save(cd1);


            System.out.println("Initial data seeded.");
        }
    }
}