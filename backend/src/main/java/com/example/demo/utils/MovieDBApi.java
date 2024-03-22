package com.example.demo.utils;

import com.example.demo.model.Film;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class MovieDBApi {

    private final static String MOVIEDB_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNzJiODY1OWU2Y2YwZDBiNmZiMDEyN2YzM2M3ZWNjZCIsInN1YiI6IjY1ZmMzODczN2Y2YzhkMDE2MzZjM2RhMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SqvYHCNFaOYtxIG-086TVO0BfjTzH74EsYD169yJ1rk";

    public static List<Film> generateFilms(int amount) {
        OkHttpClient client = new OkHttpClient();
        List<Film> movieList = new ArrayList<>();

        Map<Integer, String> genreMap = getGenreMap();

        int page = 0;
        while (movieList.size() < amount) {

            page++;
            Request filmsRequest = new Request.Builder()
                    .url("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=popularity.desc&page=" + page)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", MOVIEDB_TOKEN)
                    .build();


            try (Response filmsResponse = client.newCall(filmsRequest).execute()) {

                if (filmsResponse.isSuccessful()) {
                    String responseBody = filmsResponse.body().string();

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode root = objectMapper.readTree(responseBody);
                    JsonNode results = root.get("results");

                    System.out.println(results);

                    Iterator<JsonNode> iterator = results.elements();
                    while (iterator.hasNext() && movieList.size() < amount) {

                        JsonNode movieNode = iterator.next();

                        String title = movieNode.get("title").asText();
                        String language = movieNode.get("original_language").asText();
                        String overview = movieNode.get("overview").asText();
                        String imagePath = movieNode.get("poster_path").asText();
                        double rating = movieNode.get("vote_average").asDouble();
                        int year = LocalDate.parse(movieNode.get("release_date").asText()).getYear();
                        boolean adult = movieNode.get("adult").asBoolean();
                        JsonNode genreIdsNode = movieNode.get("genre_ids");

                        StringBuilder genres = new StringBuilder();
                        if (genreIdsNode != null && genreIdsNode.isArray()) {
                            for (JsonNode genreIdNode : genreIdsNode) {
                                int genreId = genreIdNode.asInt();
                                String genreName = genreMap.get(genreId);
                                if (genreName != null) {
                                    if (!genres.isEmpty()) {
                                        genres.append(", ");
                                    }
                                    genres.append(genreName);
                                }
                            }
                        }

                        Film film = new Film();
                        film.setTitle(title);
                        film.setLanguage(language);
                        film.setOverview(overview);
                        film.setImagePath(imagePath);
                        film.setRating(rating);
                        film.setDateYear(year);
                        film.setAdult(adult);
                        film.setGenre(genres.toString());

                        movieList.add(film);
                    }

                } else {
                    System.out.println("Film request failed with code: " + filmsResponse.code());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return movieList;
    }


    public static Map<Integer, String> getGenreMap() {
        OkHttpClient client = new OkHttpClient();
        Map<Integer, String> genreMap = new HashMap<>();

        Request genresRequest = new Request.Builder()
                .url("https://api.themoviedb.org/3/genre/movie/list?language=en")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", MOVIEDB_TOKEN)
                .build();

        try (Response genresResponse = client.newCall(genresRequest).execute()) {

            if (genresResponse.isSuccessful()) {
                String responseBody = genresResponse.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(responseBody);
                JsonNode genres = root.get("genres");
                for (JsonNode genreNode : genres) {
                    int genreId = genreNode.get("id").asInt();
                    String genreName = genreNode.get("name").asText();
                    genreMap.put(genreId, genreName);
                }
            } else {
                System.out.println("Genres request failed with code: " + genresResponse.code());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return genreMap;
    }
}
