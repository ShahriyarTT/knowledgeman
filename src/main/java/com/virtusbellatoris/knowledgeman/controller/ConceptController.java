package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.DTO.ConceptDTO;
import com.virtusbellatoris.knowledgeman.model.Concept;
import com.virtusbellatoris.knowledgeman.service.ConceptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/concepts")
public class ConceptController {

    private final ConceptService conceptService;
    public ConceptController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @GetMapping
    public List<Concept> getAllConcepts(){
        return conceptService.getAllConcepts();
    }

    @GetMapping("/{id}")
    public Concept getConceptById(@PathVariable Integer id){
        return conceptService.getConceptById(id);
    }

    @PostMapping
    public Concept saveConcept(@RequestBody ConceptDTO conceptDTO){
        return conceptService.saveConcept(conceptDTO);
    }

    @PutMapping("/{id}")
    public Concept updateConcept(@PathVariable Integer id, @RequestBody ConceptDTO conceptDTO){
        return conceptService.updateConcept(id, conceptDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteConcept(@PathVariable Integer id){
        conceptService.deleteConcept(id);
    }


}
