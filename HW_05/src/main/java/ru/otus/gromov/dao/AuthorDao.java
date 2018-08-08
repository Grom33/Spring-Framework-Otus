package ru.otus.gromov.dao;

import ru.otus.gromov.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    boolean insert(Author author);

    Author getById(int id);

    List<Author> getAll();

    Author getByName(String name);

    boolean delete(int id);

    List<Author> getByArrayID(Integer[] arrayOfId);
}
