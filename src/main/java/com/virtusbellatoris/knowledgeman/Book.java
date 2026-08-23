package com.virtusbellatoris.knowledgeman;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.core.metrics.StartupStep;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String title; // Title
    private String author; // Author
    private Integer year; // First Publication Year
    private String description; // Short Description / Core Message / Moral Insight

    @ManyToMany
    private Set<Tag> tags;

    // Constructors
    protected Book() {
    }

    public Book(String title,
                String author,
                Integer year,
                String description,
                Set<Tag> tags) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.tags = tags;
    }

    // Getters
    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Integer getYear() {
        return year;
    }
    public String getDescription() {
        return description;
    }
    public Set<Tag> getTags() { return tags; }

    // Setters
    public void setId(Integer id) {                  this.id = id;    }
    public void setTitle(String title) {             this.title = title;    }
    public void setAuthor(String author) {           this.author = author;    }
    public void setYear(Integer year) {              this.year = year;    }
    public void setDescription(String description) { this.description = description;    }
    public void setTags(Set<Tag> tags) {             this.tags = tags;    }
}
