package com.example.demo.utils;

import com.example.demo.model.Film;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieDBApi {

    public static List<Film> generateFilms(int page) {
        OkHttpClient client = new OkHttpClient();

        String movieDBToken = System.getenv("MOVIEDB_TOKEN");

        System.out.println(movieDBToken);
        if (movieDBToken == null || movieDBToken.isEmpty()) {
            System.err.println("MOVIEDB_TOKEN environment variable is not set");
            return new ArrayList<>();
        }

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=popularity.desc&page=" + page)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + movieDBToken)
                .build();

        List<Film> movieList = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(responseBody);
                JsonNode results = root.get("results");

                System.out.println(results);

                Iterator<JsonNode> iterator = results.elements();
                while (iterator.hasNext()) {

                    JsonNode movieNode = iterator.next();

                    String title = movieNode.get("title").asText();
                    String language = movieNode.get("original_language").asText();
                    String overview = movieNode.get("overview").asText();
                    String imagePath = movieNode.get("poster_path").asText();
                    double rating = movieNode.get("vote_average").asDouble();
                    int year = LocalDate.parse(movieNode.get("release_date").asText()).getYear();


                    Film film = new Film();
                    film.setTitle(title);
                    film.setLanguage(language);
                    film.setOverview(overview);
                    film.setImagePath(imagePath);
                    film.setRating(rating);
                    film.setDateYear(year);

                    movieList.add(film);
                }

            } else {
                System.out.println("Request failed with code: " + response.code());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieList;
    }
}
