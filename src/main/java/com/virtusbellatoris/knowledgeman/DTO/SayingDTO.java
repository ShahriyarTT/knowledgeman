package com.virtusbellatoris.knowledgeman.DTO;

import java.util.Set;

public class SayingDTO {

    private String expression;
    private String author;
    private String context;
    private String lesson;
    private String origin;
    private Set<Integer> tags;

    // Constructors
    public SayingDTO(String expression, String author, String context, String lesson, String origin, Set<Integer> tags) {
        this.expression = expression;
        this.author = author;
        this.context = context;
        this.lesson = lesson;
        this.origin = origin;
        this.tags = tags;
    }

    // Getters
    public String getExpression() {        return expression;    }
    public String getAuthor() {        return author;    }
    public String getContext() {        return context;    }
    public String getLesson() {        return lesson;    }
    public String getOrigin() {        return origin;    }
    public Set<Integer> getTags() {        return tags;    }


}
