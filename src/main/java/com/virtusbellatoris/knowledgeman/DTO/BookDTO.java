package com.virtusbellatoris.knowledgeman.DTO;

import java.util.Set;

public class BookDTO {

    private String title;
    private String author;
    private Integer year;
    private String description;
    private Set<String> tagNames;

    // Constructors
    protected BookDTO() {
    }

    public BookDTO(String title, String author, Integer year, String description, Set<String> tagNames) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.description = description;
        this.tagNames = tagNames;
    }

    // Getters
    public String getTitle() { return title;    }
    public String getAuthor() { return author;    }
    public Integer getYear() { return year;    }
    public String getDescription() { return description;    }
    public Set<String> getTagNames() { return tagNames;    }


}
