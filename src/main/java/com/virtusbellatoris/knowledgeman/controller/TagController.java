package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.model.Tag;
import com.virtusbellatoris.knowledgeman.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tags")
public class TagController {

    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    /*
    @GetMapping("/{id}")
    public Tag getTagByName(@PathVariable Integer id) {
        return tagService.getTagById(id);
    }

     */

    @PostMapping
    public Tag saveTag(@RequestBody Tag tag){
        return tagService.saveTag(tag);
    }

    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Integer id, @RequestBody Tag tag){
        return tagService.updateTag(id, tag);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Integer id){
        tagService.deleteTag(id);
    }


}
