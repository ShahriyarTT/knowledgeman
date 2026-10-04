package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.DTO.SayingDTO;
import com.virtusbellatoris.knowledgeman.model.Saying;
import com.virtusbellatoris.knowledgeman.service.SayingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sayings")
public class SayingController {

    private final SayingService sayingService;
    public SayingController(SayingService sayingService) {
        this.sayingService = sayingService;
    }

    @GetMapping
    public List<Saying> getAllSayings(){
        return sayingService.getAllSayings();
    }

    @GetMapping("/{id}")
    public Saying getSayingById(@PathVariable Integer id){
        return sayingService.getSayingById(id);
    }

    @PostMapping
    public Saying saveSaying(@RequestBody SayingDTO sayingDTO){
        return sayingService.saveSaying(sayingDTO);
    }

    @PutMapping("/{id}")
    public Saying updateSaying(@PathVariable Integer id, @RequestBody SayingDTO sayingDTO){
        return sayingService.updateSaying(id, sayingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSaying(@PathVariable Integer id){
        sayingService.deleteSaying(id);
    }

}
