package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
