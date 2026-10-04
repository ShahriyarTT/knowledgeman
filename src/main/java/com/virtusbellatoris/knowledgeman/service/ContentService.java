package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.DTO.ContentDTO;
import com.virtusbellatoris.knowledgeman.model.Content;
import com.virtusbellatoris.knowledgeman.model.Tag;
import com.virtusbellatoris.knowledgeman.repository.ContentRepository;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;

    // Constructor
    public ContentService(ContentRepository contentRepository, TagRepository tagRepository) {
        this.contentRepository = contentRepository;
        this.tagRepository = tagRepository;
    }

    // GET
    public List<Content> getAllContents(){
        return contentRepository.findAll();
    }

    public Content getContentById(Integer id){
        return contentRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This content does not exist."));
    }

    // SAVE
    public Content saveContent(ContentDTO contentDTO){

        if (contentRepository.findByName(contentDTO.getName()).isPresent()){
            throw new RuntimeException("This content already exists.");
        }
        else {
            Content content = new Content (
                    contentDTO.getName(),
                    contentDTO.getDescription(),
                    contentDTO.getLinks(),
                    new HashSet<>()
            );

            Set<Tag> tags = new HashSet<>();
            for (Integer tagId : contentDTO.getTags()){
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(()-> new RuntimeException("Tag " + tagId + " not found."));
                tags.add(tag);
            }

            content.setTags(tags);
            return contentRepository.save(content);
        }
    }

    // UPDATE
    public Content updateContent(Integer id, ContentDTO updatedContentDTO){
        Content existingContent = contentRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This content does not exist."));

        existingContent.setName(updatedContentDTO.getName());
        existingContent.setDescription(updatedContentDTO.getDescription());
        existingContent.setLinks(updatedContentDTO.getLinks());

        Set<Tag> tags = new HashSet<>();
        for(Integer tagId : updatedContentDTO.getTags()){
            Tag tag = tagRepository.findById(tagId)
                .orElseThrow(()->
                    new RuntimeException("Tag " + tagId + " not found." ));
            tags.add(tag);
        }

        existingContent.setTags(tags);
        return contentRepository.save(existingContent);
    }

    // DELETE
    public void deleteContent(Integer id){
        Content content = contentRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This content does not exist."));
        contentRepository.delete(content);
    }


}
