package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.gromov.dao.BookDao;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.to.BoookWithPrintableFields;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao repository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public BookServiceImpl(BookDao repository, AuthorService authorService, GenreService genreService) {
        this.repository = repository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public void insert(Book book) {
        if (book.isNew()) {
            repository.insert(book);
        } else {
            repository.update(book);
        }
    }

    @Override
    public Book getById(int id) {
        Book book = repository.getById(id);
        if (book == null) throw new NotFoundException("Book with id = " + id + " not found!");
        return book;
    }

    @Override
    public Book getByName(String name) {
        Book book = repository.getByName(name);
        if (book == null) throw new NotFoundException("Book with name = " + name + " not found!");
        return book;
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    public BoookWithPrintableFields getPrintableBook(Book book) {
        BoookWithPrintableFields printableBook = new BoookWithPrintableFields(book);
        printableBook.setAuthors(authorService.getbyArrayID(book.getAuthors().toArray(new Integer[0])));
        printableBook.setGenres(genreService.getbyArrayID(book.getGenres().toArray(new Integer[0])));
        return printableBook;
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
