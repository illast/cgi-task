package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.repository.FilmRepository;
import com.example.demo.utils.MovieDBApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public Page<Film> getFilms(int page, int size, String genre, Integer year, String language, Integer rating) {
        Pageable pageable = PageRequest.of(page, size);
        return filmRepository.findFilteredFilms(genre, year, language, rating, pageable);
    }

    public void addFilm(Film film) {
        filmRepository.save(film);
    }

    public void generateFilms(int amount) {
        List<Film> films = MovieDBApi.generateFilms(amount);
        filmRepository.saveAll(films);
    }
}
