package com.virtusbellatoris.knowledgeman.DTO;

import java.util.Set;

public class BookDTO {

    private String title;
    private String author;
    private Integer year;
    private String description;
    private Set<String> tags;

    // Constructors
    public BookDTO(String title, String author, Integer year, String description, Set<String> tags) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.tags = tags;
    }

    // Getters
    public String getTitle() { return title;    }
    public String getAuthor() { return author;    }
    public Integer getYear() { return year;    }
    public String getDescription() { return description;    }
    public Set<String> getTags() { return tags;    }


}
