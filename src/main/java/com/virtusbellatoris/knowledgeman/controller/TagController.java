package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.Tag;
import com.virtusbellatoris.knowledgeman.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/test/tags")
public class TagController {

    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /*
    @GetMapping("/tags")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }
     */
    @GetMapping
    public List<Tag> getAllTags() {
        // one line — ask tagService for all tags and return them
        return tagService.getAllTags();
    }

    @GetMapping("/{name}")
    public Tag getTagByName(@PathVariable String name) {
        return tagService.getTagByName(name);
    }

    @PostMapping
    public Tag saveTag(@RequestBody Tag tag){
        return tagService.saveTag(tag);
    }

    @PutMapping("/{name}")
    public Tag updateTag(@PathVariable String name, @RequestBody Tag tag){
        return tagService.updateTag(name, tag);
    }

    @DeleteMapping("/{name}")
    public void deleteTag(@PathVariable String name){
                tagService.deleteTag(name);
    }


}
