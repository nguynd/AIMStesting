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
        if (bookRepository.count() == 0 && cdRepository.count() == 0) {
            System.out.println("Seeding initial data...");

            Book book1 = new Book();
            book1.setTitle("Nhà Giả Kim");
            book1.setCategory("Tiểu thuyết");
            book1.setPrice(109000);
            book1.setImageUrl("nha-gia-kim.jpg"); // Chỉ lưu tên file
            bookRepository.save(book1);

            Book book2 = new Book();
            book2.setTitle("Đắc Nhân Tâm");
            book2.setCategory("Sách kỹ năng");
            book2.setPrice(120000);
            book2.setImageUrl("dac-nhan-tam.jpg"); // Chỉ lưu tên file
            bookRepository.save(book2);

            CD cd1 = new CD();
            cd1.setTitle("ABBA Gold");
            cd1.setCategory("Âm nhạc");
            cd1.setPrice(250000);
            cd1.setImageUrl("abba-gold.png"); // Chỉ lưu tên file
            cdRepository.save(cd1);

            System.out.println("Initial data seeded.");
        }
    }
}