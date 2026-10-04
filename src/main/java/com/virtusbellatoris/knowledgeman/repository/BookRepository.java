package com.virtusbellatoris.knowledgeman.repository;

import com.virtusbellatoris.knowledgeman.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
}
