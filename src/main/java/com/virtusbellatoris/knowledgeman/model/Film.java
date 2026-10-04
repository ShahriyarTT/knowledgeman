package com.virtusbellatoris.knowledgeman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Film {

    @Id
    @GeneratedValue
    private Integer id;

    private String title; // Title
    private String directorProducer; // Director/Producer
    private Integer year; // Release Year
    private String description; // Short Description / Core Message / Moral Insight
    private Set<String> links; // Link

    @ManyToMany
    private Set<Tag> tags;

    // Constructors
    protected Film() {
    }

    public Film(String title, String directorProducer, Integer year, String description, Set<String> links, Set<Tag> tags) {
        this.title = title;
        this.directorProducer = directorProducer;
        this.year = year;
        this.description = description;
        this.links = links;
        this.tags = tags;
    }

    private void links() {
    }

    // Getters
    public Integer getId() {        return id;    }
    public String getTitle() {        return title;    }
    public String getDirectorProducer() {        return directorProducer;    }
    public Integer getYear() {        return year;    }
    public String getDescription() {        return description;    }
    public Set<String> getLinks() {        return links;    }
    public Set<Tag> getTags() {        return tags;    }

    // Setters
    public void setTitle(String title) {        this.title = title;    }
    public void setDirectorProducer(String directorProducer) {        this.directorProducer = directorProducer;    }
    public void setYear(Integer year) {        this.year = year;    }
    public void setDescription(String description) {        this.description = description;    }
    public void setLinks(Set<String> links) {        this.links = links;    }
    public void setTags(Set<Tag> tags) {        this.tags = tags;    }


}
