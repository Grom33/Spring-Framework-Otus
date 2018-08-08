package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.dao.BookDao;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.util.exception.EntityNotFound;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao repository;

    @Autowired
    public BookServiceImpl(BookDao repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = false)
    public void insert(Book book) {
        repository.insert(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long id) {
        try {
            return repository.getById(id);
        } catch (EntityNotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Book getByName(String name) {
        try {
            return repository.getByName(name);
        } catch (EntityNotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        try {
            repository.delete(repository.getById(id));
        } catch (EntityNotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
