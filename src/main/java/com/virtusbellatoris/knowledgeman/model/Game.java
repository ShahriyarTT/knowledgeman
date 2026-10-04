package com.virtusbellatoris.knowledgeman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private Integer id;

    private String name; // name
    private String rule; // Rules & Description

    @ManyToMany
    private Set<Tag> tags;

    // Constructors
    protected Game() {
    }

    public Game(String name, String rule, Set<Tag> tags) {
        this.name = name;
        this.rule = rule;
        this.tags = tags;
    }

    // Getters
    public Integer getId() {        return id;    }
    public String getName() {        return name;    }
    public String getRule() {        return rule;    }
    public Set<Tag> getTags() {        return tags;    }

    // Setters
    public void setName(String name) {        this.name = name;    }
    public void setRule(String rule) {        this.rule = rule;    }
    public void setTags(Set<Tag> tags) {        this.tags = tags;    }


}
