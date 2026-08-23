package com.virtusbellatoris.knowledgeman;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
// import org.springframework.data.annotation.Id;
import jakarta.persistence.Id;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    private TagCategory tagCategory;

    public enum ResourceType {
        BOOK,
        MOVIE,
        DOCUMENTARY,
        GAME,
        SAYING,
        CONCEPT,
        CONTENT,
        ANY
    }

    public enum TagCategory {
        THEME,
        GENRE,
        TYPE,
        AGE,
        SKILL,
        OTHER
    }

    // Constructors
    protected Tag() { // why need empty? and why protected?
    }

    public Tag(String name,
               String description,
               ResourceType resourceType,
               TagCategory tagCategory) {
        this.name = name;
        this.description = description;
        this.resourceType = resourceType;
        this.tagCategory = tagCategory;
    }

    // Getters
    public Integer getId() {        return id;    }
    public String getName() {        return name;    }
    public String getDescription() {        return description;    }
    public ResourceType getResourceType() {        return resourceType;    }
    public TagCategory getTagCategory() {        return tagCategory;    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
    public void setTagCategory(TagCategory tagCategory) {
        this.tagCategory = tagCategory;
    }
}


