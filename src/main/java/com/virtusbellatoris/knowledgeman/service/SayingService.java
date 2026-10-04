package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.DTO.SayingDTO;
import com.virtusbellatoris.knowledgeman.model.Saying;
import com.virtusbellatoris.knowledgeman.model.Tag;
import com.virtusbellatoris.knowledgeman.repository.SayingRepository;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SayingService {

    private final SayingRepository sayingRepository;
    private final TagRepository tagRepository;

    // Constructor
    public SayingService(SayingRepository sayingRepository, TagRepository tagRepository) {
        this.sayingRepository = sayingRepository;
        this.tagRepository = tagRepository;
    }

    // GET
    public List<Saying> getAllSayings(){
        return sayingRepository.findAll();
    }

    public Saying getSayingById(Integer id){
        return sayingRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This saying does not exist."));
    }

    // SAVE
    public Saying saveSaying(SayingDTO sayingDTO){
        if (sayingRepository.findByExpression(sayingDTO.getExpression()).isPresent()){
            throw new RuntimeException("This saying already exists.");
        }
        else {
            Saying saying = new Saying (
                    sayingDTO.getExpression(),
                    sayingDTO.getAuthor(),
                    sayingDTO.getContext(),
                    sayingDTO.getLesson(),
                    sayingDTO.getOrigin(),
                    new HashSet<>()
            );

            Set<Tag> tags = new HashSet<>();
            for (Integer tagId : sayingDTO.getTags()){
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(()-> new RuntimeException("Tag " + tagId + " not found."));
                tags.add(tag);
            }

            saying.setTags(tags);
            return sayingRepository.save(saying);
        }
    }

    // UPDATE
    public Saying updateSaying(Integer id, SayingDTO updatedSayingDTO){
        Saying existingSaying = sayingRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This saying does not exist."));

        existingSaying.setExpression(updatedSayingDTO.getExpression());
        existingSaying.setAuthor(updatedSayingDTO.getAuthor());
        existingSaying.setContext(updatedSayingDTO.getContext());
        existingSaying.setLesson(updatedSayingDTO.getLesson());
        existingSaying.setOrigin(updatedSayingDTO.getOrigin());

        Set<Tag> tags = new HashSet<>();
        for (Integer tagId : updatedSayingDTO.getTags()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag " + tagId + " not found."));
            tags.add(tag);
        }

        existingSaying.setTags(tags);
        return sayingRepository.save(existingSaying);
    }

    // DELETE
    public void deleteSaying(Integer id){
        Saying saying = sayingRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This saying does not exist."));
        sayingRepository.delete(saying);
    }


}
