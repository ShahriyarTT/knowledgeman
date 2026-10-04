package com.virtusbellatoris.knowledgeman.repository;

import com.virtusbellatoris.knowledgeman.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Integer> {
    Optional<Content> findByName(String name);
}
