package com.example.demo.controller;

import com.example.demo.dto.FilmDto;
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
    public Page<FilmDto> getFilms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer rating) {
        return filmService.getFilms(page, size, genre, year, language, rating);
    }

    @PostMapping
    public void addFilm(@RequestBody FilmDto filmDto) {
        filmService.addFilm(filmDto);
    }

    @GetMapping("/generate")
    public void generateFilms(@RequestParam(defaultValue = "400") int amount) {
        filmService.generateFilms(amount);
    }

    @GetMapping("/{id}")
    public FilmDto getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }
}
