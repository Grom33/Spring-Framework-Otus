package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.repository.BookRepository;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Book with id =" + id + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Book with name: " + name + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundException("Book with id = " + id + " not found!")));
    }
}
