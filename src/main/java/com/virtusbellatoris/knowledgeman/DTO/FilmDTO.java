package com.virtusbellatoris.knowledgeman.DTO;

import java.util.Set;

public class FilmDTO {

    private String title;
    private String directorProducer;
    private Integer year;
    private String description;
    private Set<String> links;
    private Set<Integer> tags;

    // Constructors
    public FilmDTO(String title, String directorProducer, Integer year, String description, Set<String> links, Set<Integer> tags) {
        this.title = title;
        this.directorProducer = directorProducer;
        this.year = year;
        this.description = description;
        this.links = links;
        this.tags = tags;
    }

    // Getters
    public String getTitle() {        return title;    }
    public String getDirectorProducer() {        return directorProducer;    }
    public Integer getYear() {        return year;    }
    public String getDescription() {        return description;    }
    public Set<String> getLinks() {        return links;    }
    public Set<Integer> getTags() {        return tags;    }


}
