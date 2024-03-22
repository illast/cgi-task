package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FilmDto {
    private Long id;
    private String title;
    private int dateYear;
    private double rating;
    private String overview;
    private String genre;
    private boolean adult;
    private String language;
    private String imagePath;
}
