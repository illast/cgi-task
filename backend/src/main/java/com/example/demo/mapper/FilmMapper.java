package com.example.demo.mapper;

import com.example.demo.dto.FilmDto;
import com.example.demo.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FilmMapper {

    FilmDto entityToDto(Film film);
    Film dtoToEntity(FilmDto filmDto);
    List<FilmDto> toDtoList(List<Film> films);
}
