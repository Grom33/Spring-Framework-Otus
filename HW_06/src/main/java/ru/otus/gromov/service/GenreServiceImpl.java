package ru.otus.gromov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.dao.GenreDao;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.EntityNotFound;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao repository;

    public GenreServiceImpl(GenreDao repository) {
        this.repository = repository;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional()
    public Genre insert(Genre genre) {
        return repository.insert(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getById(Long id) {
        try {
            return repository.getById(id);
        } catch (EntityNotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getByName(String name) {
        try {
            return repository.getByName(name);
        } catch (EntityNotFound e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
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
