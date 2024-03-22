package com.example.demo.service;

import com.example.demo.dto.FilmDto;
import com.example.demo.dto.UserDto;
import com.example.demo.mapper.FilmMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final UserMapper userMapper;
    private final FilmMapper filmMapper;

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public void addUser(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        userRepository.save(user);
    }

    public List<FilmDto> getUserFilms(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        List<Film> films = user.getFilms();
        return filmMapper.toDtoList(films);
    }

    public void addFilmToUser(Long userId, Long filmId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new IllegalArgumentException("Film not found with id: " + filmId));
        film.setUser(user);
        filmRepository.saveAndFlush(film);
    }
}
