package com.virtusbellatoris.knowledgeman.repository;

import com.virtusbellatoris.knowledgeman.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer>{
    Optional<Tag> findByName(String name);
}

