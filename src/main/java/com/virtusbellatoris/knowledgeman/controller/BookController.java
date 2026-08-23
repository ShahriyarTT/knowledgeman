package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.Book;
import com.virtusbellatoris.knowledgeman.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/test/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return BookService.getAllBooks();
    };

    @GetMapping("/{title}")
    public Book getBookByTitle(@PathVariable String title){
        return BookService.getBookByTitle(title);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return BookService.saveBook(book);
    }

    @PutMapping("/{title}")
    public Book updateBook(@PathVariable String title, @RequestBody Book book){
        return BookService.updateBook(title, book);
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title){
        BookService.deleteBook(title);
    }


}
