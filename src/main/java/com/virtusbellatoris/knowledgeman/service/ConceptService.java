package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.DTO.ConceptDTO;
import com.virtusbellatoris.knowledgeman.model.Concept;
import com.virtusbellatoris.knowledgeman.model.Tag;
import com.virtusbellatoris.knowledgeman.repository.ConceptRepository;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ConceptService {

    private final ConceptRepository conceptRepository;
    private final TagRepository tagRepository;

    // Constructor
    public ConceptService(ConceptRepository conceptRepository, TagRepository tagRepository) {
        this.conceptRepository = conceptRepository;
        this.tagRepository = tagRepository;
    }

    // GET
    public List<Concept> getAllConcepts(){
        return conceptRepository.findAll();
    }

    public Concept getConceptById(Integer id){
        return conceptRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This concept does not exist."));
    }

    // SAVE
    public Concept saveConcept(ConceptDTO conceptDTO){

        if (conceptRepository.findByTitle(conceptDTO.getTitle()).isPresent()){
            throw new RuntimeException("This concept already exists.");
        }
        else {
            Concept concept = new Concept (
                    conceptDTO.getTitle(),
                    conceptDTO.getAuthor(),
                    conceptDTO.getDefinition(),
                    conceptDTO.getDescription(),
                    conceptDTO.getImplementation(),
                    new HashSet<>()
            );

            Set<Tag> tags = new HashSet<>();
            for (Integer tagId : conceptDTO.getTags()){
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(()-> new RuntimeException("Tag " + tagId + " not found."));
                tags.add(tag);
            }

            concept.setTags(tags);
            return conceptRepository.save(concept);
        }
    }

    // UPDATE
    public Concept updateConcept(Integer id, ConceptDTO updatedConceptDTO){
        Concept existingConcept = conceptRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This concept does not exist."));

        existingConcept.setTitle(updatedConceptDTO.getTitle());
        existingConcept.setAuthor(updatedConceptDTO.getAuthor());
        existingConcept.setDefinition(updatedConceptDTO.getDefinition());
        existingConcept.setDescription(updatedConceptDTO.getDescription());
        existingConcept.setImplementation(updatedConceptDTO.getImplementation());

        Set<Tag> tags = new HashSet<>();
        for (Integer tagId : updatedConceptDTO.getTags()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag " + tagId + " not found."));
            tags.add(tag);
        }

        existingConcept.setTags(tags);
        return conceptRepository.save(existingConcept);
    }

    // DELETE
    public void deleteConcept(Integer id){
        Concept concept = conceptRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This concept does not exist."));
        conceptRepository.delete(concept);
    }


}
