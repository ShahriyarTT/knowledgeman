package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.model.Book;
import com.virtusbellatoris.knowledgeman.DTO.BookDTO;
import com.virtusbellatoris.knowledgeman.model.Tag;
import com.virtusbellatoris.knowledgeman.repository.BookRepository;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// now build BookService. It needs:
@Service
public class BookService {

    // You'll need both BookRepository and TagRepository injected into BookService.
    private final BookRepository bookRepository;
    private final TagRepository tagRepository;

    // Constructor
    public BookService(BookRepository bookRepository, TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.tagRepository = tagRepository;
    }

    // GET
    public List<Book> getAllBooks (){
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id){
        return bookRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("This book does not exist."));
    }

    // SAVE
    public Book saveBook(BookDTO bookDTO){

        if (bookRepository.findByTitle(bookDTO.getTitle()).isPresent()){
            throw new RuntimeException("This book already exists.");
        }
        else {
            Book book = new Book(
                    bookDTO.getTitle(),
                    bookDTO.getAuthor(),
                    bookDTO.getYear(),
                    bookDTO.getDescription(),
                    new HashSet<>()
            );

            Set<Tag> tags = new HashSet<>();
            for (String tagName : bookDTO.getTags()) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseThrow(() -> new RuntimeException("Tag " + tagName + " not found."));
                tags.add(tag);
            }

            book.setTags(tags);
            return bookRepository.save(book);

        }

    }

    // UPDATE
    public Book updateBook(Integer id, BookDTO updatedBookDTO){
        Book existingBook = bookRepository.findById(id)
            .orElseThrow(()->
                    new RuntimeException("This book does not exist.")
            );

        existingBook.setTitle(updatedBookDTO.getTitle());
        existingBook.setAuthor(updatedBookDTO.getAuthor());
        existingBook.setYear(updatedBookDTO.getYear());
        existingBook.setDescription(updatedBookDTO.getDescription());

        Set<Tag> tags = new HashSet<>();
        for (String tagName : updatedBookDTO.getTags()) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseThrow(() -> new RuntimeException("Tag " + tagName + " not found." ));
            tags.add(tag);
        }

        existingBook.setTags(tags);
        return bookRepository.save(existingBook);
    }

    // DELETE
    public void deleteBook(Integer id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("This book does not exist.")
                );
        bookRepository.delete(book);
    }


}
