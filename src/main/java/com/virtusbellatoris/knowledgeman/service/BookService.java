package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.Book;
import com.virtusbellatoris.knowledgeman.DTO.BookDTO;
import com.virtusbellatoris.knowledgeman.Tag;
import com.virtusbellatoris.knowledgeman.repository.BookRepository;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// now build BookService. It needs:
@Service
public class BookService {

    // You'll need both BookRepository and TagRepository injected into BookService.
    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    public BookService(BookRepository bookRepository, TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.tagRepository = tagRepository;
    }

    // getAllBooks() — same pattern as TagService
    public List<Book> getAllBooks (){
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title){
        return bookRepository.findByName(title);
    }

    // saveBook(BookDTO dto) — this is the interesting one
    public Book saveBook(BookDTO bookDTO){
        // Create a new Book object from the dto fields
        // For each tag name in dto.getTagNames() — fetch the tag from tagRepository using findByName()
        // If a tag name doesn't exist — what should happen? Think about it
        // Collect the found tags into a Set<Tag> and set them on the book
        // Save and return the book

    }
        /*
        One thing to figure out yourself — how do you loop through dto.getTagNames()
        and fetch each tag? You've used loops in Java before. Think about what kind of loop fits here.
     */


    public Book updateBook(String title, Book updatedBook){
        Book existingBook = bookRepository.findByName(title)
            .orElseThrow(()->
                    new RuntimeException("This book does not exist.")
            );

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setYear(updatedBook.getYear());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setTags(updatedBook.getTags());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(String title){
        Book book = bookRepository.findByName(title)
                .orElseThrow(() ->
                        new RuntimeException("This book does not exist.")
                );
        bookRepository.delete(book);
    }


}
