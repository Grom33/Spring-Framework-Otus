package ru.otus.gromov.service;

import ru.otus.gromov.domain.Author;

import java.util.List;

public interface AuthorService {
    int count();

    Author insert(Author author);

    Author getById(int id);

    List<Author> getAll();

    Author getByName(String name);

    List<Author> getbyArrayID(Integer[] arrayOfId);

    void delete(int id);
}
