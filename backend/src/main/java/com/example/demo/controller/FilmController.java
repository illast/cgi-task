package com.example.demo.controller;

import com.example.demo.model.Film;
import com.example.demo.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    public Page<Film> getFilms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer rating) {
        return filmService.getFilms(page, size, genre, year, language, rating);
    }

    @PostMapping
    public void addFilm(@RequestBody Film film) {
        filmService.addFilm(film);
    }

    @GetMapping("/generate")
    public void generateFilms(@RequestParam(defaultValue = "0") int page) {
        filmService.generateFilms(page);
    }
}
