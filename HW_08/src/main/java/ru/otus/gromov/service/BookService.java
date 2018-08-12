package ru.otus.gromov.service;

import ru.otus.gromov.domain.Book;

import java.util.List;

public interface BookService {
    long count();

    Book save(Book book);

    Book getById(String id);

    Book getByName(String name);

    List<Book> getAll();

    void delete(String id);

}
