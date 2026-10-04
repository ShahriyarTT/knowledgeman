package com.virtusbellatoris.knowledgeman.repository;

import com.virtusbellatoris.knowledgeman.model.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ConceptRepository extends JpaRepository<Concept, Integer> {
    Optional<Concept> findByTitle(String title);
}
