package com.virtusbellatoris.knowledgeman.repository;

import com.virtusbellatoris.knowledgeman.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<Film> findByTitle(String title);
}
