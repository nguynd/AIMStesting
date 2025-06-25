package com.ISD.AIMS.controller;

import com.ISD.AIMS.model.Book;
import com.ISD.AIMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existing = bookService.getBookById(id);
        if (existing.isPresent()) {
            Book book = existing.get();
            book.setTitle(updatedBook.getTitle());
            book.setCategory(updatedBook.getCategory());
            book.setValue(updatedBook.getValue());
            book.setPrice(updatedBook.getPrice());
            book.setBarcode(updatedBook.getBarcode());
            book.setDescription(updatedBook.getDescription());
            book.setQuantity(updatedBook.getQuantity());
            book.setWeight(updatedBook.getWeight());
            book.setDimensions(updatedBook.getDimensions());

            book.setAuthors(updatedBook.getAuthors());
            book.setCoverType(updatedBook.getCoverType());
            book.setPublisher(updatedBook.getPublisher());
            book.setPublicationDate(updatedBook.getPublicationDate());
            book.setNumberOfPages(updatedBook.getNumberOfPages());
            book.setLanguage(updatedBook.getLanguage());
            book.setGenre(updatedBook.getGenre());
            return bookService.saveBook(book);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search/title")
    public List<Book> searchByTitle(@RequestParam String keyword) {
        return bookService.searchByTitle(keyword);
    }

    @GetMapping("/search/author")
    public List<Book> searchByAuthor(@RequestParam String author) {
        return bookService.searchByAuthor(author);
    }

    @GetMapping("/search/genre")
    public List<Book> searchByGenre(@RequestParam String genre) {
        return bookService.searchByGenre(genre);
    }
}
