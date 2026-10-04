package com.virtusbellatoris.knowledgeman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

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
    // Instead of accepting Hibernate's default name, you can explicitly define it:
    // This is often preferable because you explicitly control your database schema rather
    // than depending on Hibernate's naming strategy.
    /*
    @ManyToMany
    @JoinTable(
        name = "book_tags",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
     */

    // Constructors
    protected Book() { // Why protected?
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
    public Integer getId() {        return id;    }
    public String getTitle() {        return title;    }
    public String getAuthor() {        return author;    }
    public Integer getYear() {        return year;    }
    public String getDescription() {        return description;    }
    public Set<Tag> getTags() { return tags; }

    // Setters
    public void setTitle(String title) {             this.title = title;    }
    public void setAuthor(String author) {           this.author = author;    }
    public void setYear(Integer year) {              this.year = year;    }
    public void setDescription(String description) { this.description = description;    }
    public void setTags(Set<Tag> tags) {             this.tags = tags;    }


}
