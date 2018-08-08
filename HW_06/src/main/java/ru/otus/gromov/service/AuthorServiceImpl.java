package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.dao.AuthorDao;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.EntityNotFound;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao repository;

    @Autowired
    public AuthorServiceImpl(AuthorDao repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional()
    public Author insert(Author author) {
        return repository.insert(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(Long id) {
        try {
            return repository.getById(id);
        } catch (EntityNotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByName(String name) {
        try {
            return repository.getByName(name);
        } catch (EntityNotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
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
