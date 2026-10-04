package com.virtusbellatoris.knowledgeman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Content {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String description;
    private Set<String> links;

    @ManyToMany
    private Set<Tag> tags;

    // Constructors
    protected Content() {
    }

    public Content(String name, String description, Set<String> links, Set<Tag> tags) {
        this.name = name;
        this.description = description;
        this.links = links;
        this.tags = tags;
    }

    // Getters
    public Integer getId() {        return id;    }
    public String getName() {        return name;    }
    public String getDescription() {        return description;    }
    public Set<String> getLinks() {        return links;    }
    public Set<Tag> getTags() {        return tags;    }

    // Setters
    public void setName(String name) {        this.name = name;    }
    public void setDescription(String description) {        this.description = description;    }
    public void setLinks(Set<String> links) {        this.links = links;    }
    public void setTags(Set<Tag> tags) {        this.tags = tags;    }


}
