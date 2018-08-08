package ru.otus.gromov.service;

import ru.otus.gromov.domain.Book;

import java.util.List;

public interface BookService {
    long count();

    void insert(Book book);

    Book getById(Long id);

    Book getByName(String name);

    List<Book> getAll();

    void delete(Long id);

}
