package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.Tag;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags (){
        return tagRepository.findAll();
    };

    public Tag getTagByName(String name){
        return tagRepository.findByName(name);
    }

    public Tag saveTag(Tag tag){
        if (tagRepository.findByName(tag.getName()).isPresent()){
            throw new RuntimeException("This tag already exists.");
        }
        else {
            return tagRepository.save(tag);
        }

    };

    // which is better? send String name or Tag tag?
    // updateTag(Long id, Tag updatedTag)
    // updateTag(String name, Tag updatedTag)
    // updateTag(Tag updatedTag)
    // updateTag(Tag tag)

    public Tag updateTag(String name, Tag updatedTag){
        Tag existingTag = tagRepository.findByName(name)
            .orElseThrow(() ->
                new RuntimeException("This tag does not exist.")
            );

        existingTag.setName(updatedTag.getName());
        existingTag.setDescription(updatedTag.getDescription());
        existingTag.setResourceType(updatedTag.getResourceType());
        existingTag.setTagCategory(updatedTag.getTagCategory());
        return tagRepository.save(existingTag);
    }

    public void deleteTag(String name) {
        Tag tag = tagRepository.findByName(name)
            .orElseThrow(() ->
                new RuntimeException("This tag does not exist.")
            );
        tagRepository.delete(tag);
    }


}
