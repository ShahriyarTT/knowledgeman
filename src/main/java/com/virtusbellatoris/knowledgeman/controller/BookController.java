package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.model.Book;
import com.virtusbellatoris.knowledgeman.DTO.BookDTO;
import com.virtusbellatoris.knowledgeman.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    };

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody BookDTO bookDTO){
        return bookService.saveBook(bookDTO);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO){
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
    }


}
