package com.virtusbellatoris.knowledgeman.repository;

import com.virtusbellatoris.knowledgeman.Book;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String title);
}
