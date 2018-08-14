package ru.otus.gromov.service;

import ru.otus.gromov.domain.Genre;

import java.util.List;

public interface GenreService {
    long count();

    Genre save(Genre author);

    Genre getById(String id);

    Genre getByName(String name);

    List<Genre> getAll();

    void delete(String id);
}
