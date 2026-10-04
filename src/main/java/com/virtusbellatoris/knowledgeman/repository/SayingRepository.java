package com.virtusbellatoris.knowledgeman.repository;

import com.virtusbellatoris.knowledgeman.model.Saying;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SayingRepository extends JpaRepository<Saying, Integer> {
    Optional<Saying> findByExpression(String expression);
}
