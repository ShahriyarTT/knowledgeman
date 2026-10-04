package com.virtusbellatoris.knowledgeman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Saying {

    @Id
    @GeneratedValue
    private Integer id;

    private String expression;
    private String author;
    private String context;
    private String lesson;
    private String origin;

    @ManyToMany
    private Set<Tag> tags;

    // Constructors
    protected Saying() {
    }

    public Saying(String expression, String author, String context, String lesson, String origin, Set<Tag> tags) {
        this.expression = expression;
        this.author = author;
        this.context = context;
        this.lesson = lesson;
        this.origin = origin;
        this.tags = tags;
    }

    // Getters
    public Integer getId() {        return id;    }
    public String getExpression() {        return expression;    }
    public String getAuthor() {        return author;    }
    public String getContext() {        return context;    }
    public String getLesson() {        return lesson;    }
    public String getOrigin() {        return origin;    }
    public Set<Tag> getTags() {        return tags;    }

    // Setters
    public void setExpression(String expression) {        this.expression = expression;    }
    public void setAuthor(String author) {        this.author = author;    }
    public void setContext(String context) {        this.context = context;    }
    public void setLesson(String lesson) {        this.lesson = lesson;    }
    public void setOrigin(String origin) {        this.origin = origin;    }
    public void setTags(Set<Tag> tags) {        this.tags = tags;    }


}
