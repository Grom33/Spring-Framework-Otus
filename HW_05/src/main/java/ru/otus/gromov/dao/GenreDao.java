package ru.otus.gromov.dao;

import ru.otus.gromov.domain.Genre;

import java.util.List;

public interface GenreDao {
    int count();

    boolean insert(Genre author);

    Genre getById(int id);

    Genre getByName(String name);

    List<Genre> getAll();

    boolean delete(int id);

    List<Genre> getByArrayID(Integer[] arrayOfId);
}
