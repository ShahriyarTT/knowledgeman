package com.virtusbellatoris.knowledgeman.DTO;

import java.util.Set;

public class ContentDTO {

    private String name;
    private String description;
    private Set<String> links;
    private Set<Integer> tags;

    // Constructors
    public ContentDTO(String name, String description, Set<String> links, Set<Integer> tags) {
        this.name = name;
        this.description = description;
        this.links = links;
        this.tags = tags;
    }

    // Getters
    public String getName() {        return name;    }
    public String getDescription() {        return description;    }
    public Set<String> getLinks() {        return links;    }
    public Set<Integer> getTags() {        return tags;    }


}
