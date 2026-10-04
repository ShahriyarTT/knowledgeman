package com.virtusbellatoris.knowledgeman.DTO;

import java.util.Set;

public class ConceptDTO {

    private String title;
    private String author;
    private String definition;
    private String description;
    private String implementation;
    private Set<Integer> tags;

    // Constructors
    public ConceptDTO(String title, String author, String definition, String description, String implementation, Set<Integer> tags) {
        this.title = title;
        this.author = author;
        this.definition = definition;
        this.description = description;
        this.implementation = implementation;
        this.tags = tags;
    }

    // Getters
    public String getTitle() {        return title;    }
    public String getAuthor() {        return author;    }
    public String getDefinition() {        return definition;    }
    public String getDescription() {        return description;    }
    public String getImplementation() {        return implementation;    }
    public Set<Integer> getTags() {        return tags;    }
}
