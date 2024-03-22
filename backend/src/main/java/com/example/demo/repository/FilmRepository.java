package com.example.demo.repository;

import com.example.demo.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE " +
            "(:genre IS NULL OR LOWER(f.genre) LIKE CONCAT('%', LOWER(:genre), '%')) AND " +
            "(:year IS NULL OR f.dateYear = :year) AND " +
            "(:language IS NULL OR LOWER(f.language) = LOWER(:language)) AND " +
            "(:rating IS NULL OR f.rating >= :rating)")
    Page<Film> findFilteredFilms(String genre, Integer year, String language, Integer rating, Pageable pageable);
}
