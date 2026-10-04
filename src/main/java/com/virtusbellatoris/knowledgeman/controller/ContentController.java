package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.DTO.ContentDTO;
import com.virtusbellatoris.knowledgeman.model.Content;
import com.virtusbellatoris.knowledgeman.service.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contents")
public class ContentController {

    private final ContentService contentService;
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public List<Content> getAllContents() {
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable Integer id) {
        return contentService.getContentById(id);
    }

    @PostMapping
    public Content saveContent(@RequestBody ContentDTO contentDTO) {
        return contentService.saveContent(contentDTO);
    }

    @PutMapping("/{id}")
    public Content updateContent(@PathVariable Integer id, @RequestBody ContentDTO updatedContentDTO) {
        return contentService.updateContent(id, updatedContentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id) {
        contentService.deleteContent(id);
    }
}
