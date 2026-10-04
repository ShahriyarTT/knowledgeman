package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.DTO.FilmDTO;
import com.virtusbellatoris.knowledgeman.model.Film;
import com.virtusbellatoris.knowledgeman.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/films")
public class FilmController {

    private final FilmService filmService;
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Integer id){
        return filmService.getFilmById(id);
    }

    @PostMapping
    public Film saveFilm(@RequestBody FilmDTO filmDTO){
        return filmService.saveFilm(filmDTO);
    }

    @PutMapping("/{id}")
    public Film updateFilm(@PathVariable Integer id, @RequestBody FilmDTO filmDTO){
        return filmService.updateFilm(id,filmDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Integer id){
        filmService.deleteFilm(id);
    }
}
