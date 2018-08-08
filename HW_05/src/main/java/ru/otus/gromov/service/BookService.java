package ru.otus.gromov.service;

import ru.otus.gromov.domain.Book;
import ru.otus.gromov.to.BoookWithPrintableFields;

import java.util.List;

public interface BookService {
    int count();

    void insert(Book book);

    Book getById(int id);

    Book getByName(String name);

    List<Book> getAll();

    BoookWithPrintableFields getPrintableBook(Book book);

    void delete(int id);
}
