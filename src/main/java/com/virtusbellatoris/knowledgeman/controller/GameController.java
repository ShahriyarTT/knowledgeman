package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.DTO.GameDTO;
import com.virtusbellatoris.knowledgeman.model.Game;
import com.virtusbellatoris.knowledgeman.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/games")
public class GameController {

    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Integer id){
        return gameService.getGameById(id);
    }

    @PostMapping
    public Game saveGame(@RequestBody GameDTO gameDTO){
        return gameService.saveGame(gameDTO);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Integer id, @RequestBody GameDTO gameDTO) {
        return gameService.updateGame(id, gameDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Integer id){
        gameService.deleteGame(id);
    }
}
