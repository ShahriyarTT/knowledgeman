package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.DTO.GameDTO;
import com.virtusbellatoris.knowledgeman.model.Game;
import com.virtusbellatoris.knowledgeman.model.Tag;
import com.virtusbellatoris.knowledgeman.repository.GameRepository;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final TagRepository tagRepository;

    // Constructor
    public GameService(GameRepository gameRepository, TagRepository tagRepository) {
        this.gameRepository = gameRepository;
        this.tagRepository = tagRepository;
    }

    // GET
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Game getGameById(Integer id){
        return gameRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This game does not exist."));
    }

    // SAVE
    public Game saveGame(GameDTO gameDTO){
        if (gameRepository.findByName(gameDTO.getName()).isPresent()){
            throw new RuntimeException("This game already exists.");
        }
        else {
            Game game = new Game (
                    gameDTO.getName(),
                    gameDTO.getRule(),
                    new HashSet<>()
            );

            Set<Tag> tags = new HashSet<>();
            for (Integer tagId : gameDTO.getTags()){
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(()-> new RuntimeException("Tag " + tagId + " not found."));
                tags.add(tag);
            }

            game.setTags(tags);
            return gameRepository.save(game);
        }
    }

    // UPDATE
    public Game updateGame(Integer id, GameDTO updatedGameDTO){
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This game does not exist."));

        existingGame.setName(updatedGameDTO.getName());
        existingGame.setRule(updatedGameDTO.getRule());


        Set<Tag> tags = new HashSet<>();
        for (Integer tagId : updatedGameDTO.getTags()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag " + tagId + " not found."));
            tags.add(tag);
        }

        existingGame.setTags(tags);
        return gameRepository.save(existingGame);
    }

    // DELETE
    public void deleteGame(Integer id){
        Game game = gameRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This game does not exist."));
        gameRepository.delete(game);
    }


}
