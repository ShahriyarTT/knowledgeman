package com.virtusbellatoris.knowledgeman.DTO;

import java.util.Set;

public class GameDTO {

    private String name;
    private String rule;
    private Set<Integer> tags;

    // Constructors
    public GameDTO(String name, String rule, Set<Integer> tags) {
        this.name = name;
        this.rule = rule;
        this.tags = tags;
    }

    // Getters
    public String getName() {        return name;    }
    public String getRule() {        return rule;    }
    public Set<Integer> getTags() {        return tags;    }


}
