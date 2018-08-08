package ru.otus.gromov.service;

import ru.otus.gromov.domain.Genre;

import java.util.List;

public interface GenreService {
    long count();

    Genre insert(Genre author);

    Genre getById(Long id);

    Genre getByName(String name);

    List<Genre> getAll();

    void delete(Long id);

}
