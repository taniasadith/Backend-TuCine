package com.upc.TuCine.TuCine.controller;

import com.upc.TuCine.TuCine.exception.ResourceNotFoundException;
import com.upc.TuCine.TuCine.model.Actor;
import com.upc.TuCine.TuCine.model.Category;
import com.upc.TuCine.TuCine.model.Film;
import com.upc.TuCine.TuCine.repository.ActorRepository;
import com.upc.TuCine.TuCine.repository.CategoryRepository;
import com.upc.TuCine.TuCine.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping ("/api/TuCine/v1")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
    private ActorRepository actorRepository;
    private CategoryRepository categoryRepository;

    public FilmController(FilmRepository filmRepository, ActorRepository actorRepository, CategoryRepository categoryRepository) {
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
        this.categoryRepository = categoryRepository;
    }

    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/films")
    public ResponseEntity<List<Film>> getAllFilms() {
        return new ResponseEntity<List<Film>>(filmRepository.findAll(), HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films
    //Method: POST
    @Transactional
    @PostMapping("/films")
    public ResponseEntity<Film> createFilm(@RequestBody Film film){
        return new ResponseEntity<Film>(filmRepository.save(film), HttpStatus.CREATED);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films/{idFilm}/actors/{idActor}
    //Method: POST
    @Transactional
    @PostMapping("/films/{idFilm}/actors/{idActor}")
    public void addActorToFilm(@PathVariable(value = "idFilm") Integer idFilm, @PathVariable(value = "idActor") Integer idActor){
        Film film = filmRepository.findById(idFilm).orElseThrow(()-> new ResourceNotFoundException("No se encuentra la película con id: " + idFilm));
        Actor actor = actorRepository.findById(idActor).orElseThrow(()-> new ResourceNotFoundException("No se encuentra el actor con id: " + idActor));

        film.getActors().add(actor);
        filmRepository.save(film);
    }

    //URL: http://localhost:8080/api/TuCine/v1/films/{idFilm}/categories/{idCategory}
    //Method: POST
    @Transactional
    @PostMapping("/films/{idFilm}/categories/{idCategory}")
    public void addCategoryToFilm(@PathVariable(value = "idFilm") Integer idFilm, @PathVariable(value = "idCategory") Integer idCategory){
        Film film = filmRepository.findById(idFilm).orElseThrow(()-> new ResourceNotFoundException("No se encuentra la película con id: " + idFilm));
        Category category = categoryRepository.findById(idCategory).orElseThrow(()-> new ResourceNotFoundException("No se encuentra la categoría con id: " + idCategory));

        film.getCategories().add(category);
        filmRepository.save(film);
    }
}
