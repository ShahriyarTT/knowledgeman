package com.virtusbellatoris.knowledgeman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Concept {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String author;
    private String definition;
    private String description;
    private String implementation;
    // private Set<Concept> relation; // like "also viewed this"

    @ManyToMany
    private Set<Tag> tags;

    // Constructors
    protected Concept() {
    }

    public Concept(String title, String author, String definition, String description, String implementation, Set<Tag> tags) {
        this.title = title;
        this.author = author;
        this.definition = definition;
        this.description = description;
        this.implementation = implementation;
        this.tags = tags;
    }

    // Getters
    public Integer getId() {        return id;    }
    public String getTitle() {        return title;    }
    public String getAuthor() {        return author;    }
    public String getDefinition() {        return definition;    }
    public String getDescription() {        return description;    }
    public String getImplementation() {        return implementation;    }
    public Set<Tag> getTags() {        return tags;    }

    // Setters
    public void setTitle(String title) {        this.title = title;    }
    public void setAuthor(String author) {        this.author = author;    }
    public void setDefinition(String definition) {        this.definition = definition;    }
    public void setDescription(String description) {        this.description = description;    }
    public void setImplementation(String implementation) {        this.implementation = implementation;    }
    public void setTags(Set<Tag> tags) {        this.tags = tags;    }


}
