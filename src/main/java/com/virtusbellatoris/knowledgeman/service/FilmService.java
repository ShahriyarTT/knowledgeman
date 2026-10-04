package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.DTO.FilmDTO;
import com.virtusbellatoris.knowledgeman.model.Film;
import com.virtusbellatoris.knowledgeman.model.Tag;
import com.virtusbellatoris.knowledgeman.repository.FilmRepository;
import com.virtusbellatoris.knowledgeman.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final TagRepository tagRepository;

    // Constructor
    public FilmService(FilmRepository filmRepository, TagRepository tagRepository) {
        this.filmRepository = filmRepository;
        this.tagRepository = tagRepository;
    }

    // GET
    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    public Film getFilmById(Integer id){
        return filmRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This film does not exist."));
    }

    // SAVE
    public Film saveFilm(FilmDTO filmDTO){
        if (filmRepository.findByTitle(filmDTO.getTitle()).isPresent()){
            throw new RuntimeException("This film already exists.");
        }
        else {
            Film film = new Film (
                    filmDTO.getTitle(),
                    filmDTO.getDirectorProducer(),
                    filmDTO.getYear(),
                    filmDTO.getDescription(),
                    filmDTO.getLinks(),
                    new HashSet<>()
            );

            Set<Tag> tags = new HashSet<>();
            for (Integer tagId : filmDTO.getTags()){
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(()-> new RuntimeException("Tag " + tagId + " not found."));
                tags.add(tag);
            }

            film.setTags(tags);
            return filmRepository.save(film);
        }
    }

    // UPDATE
    public Film updateFilm(Integer id, FilmDTO updatedFilmDTO){
        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This film does not exist."));

        existingFilm.setTitle(updatedFilmDTO.getTitle());
        existingFilm.setDirectorProducer(updatedFilmDTO.getDirectorProducer());
        existingFilm.setYear(updatedFilmDTO.getYear());
        existingFilm.setDescription(updatedFilmDTO.getDescription());
        existingFilm.setLinks(updatedFilmDTO.getLinks());

        Set<Tag> tags = new HashSet<>();
        for (Integer tagId : updatedFilmDTO.getTags()) {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new RuntimeException("Tag " + tagId + " not found."));
            tags.add(tag);
        }

        existingFilm.setTags(tags);
        return filmRepository.save(existingFilm);
    }

    // DELETE
    public void deleteFilm(Integer id){
        Film film = filmRepository.findById(id)
            .orElseThrow(()->
                new RuntimeException("This film does not exist."));
        filmRepository.delete(film);
    }


}
