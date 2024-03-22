package com.example.demo.controller;

import com.example.demo.dto.FilmDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @GetMapping("/{userId}/films")
    public List<FilmDto> getUserFilms(@PathVariable Long userId) {
        return userService.getUserFilms(userId);
    }

    @PostMapping("/{userId}/films")
    public void addFilmToUser(@PathVariable Long userId, @RequestBody Map<String, Long> requestBody) {
        userService.addFilmToUser(userId, requestBody.get("filmId"));
    }
}
