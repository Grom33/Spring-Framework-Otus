package ru.otus.gromov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.repository.GenreRepository;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id = " + id + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Genre with name: " + name + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id+" + id + "not found!")));
    }
}
