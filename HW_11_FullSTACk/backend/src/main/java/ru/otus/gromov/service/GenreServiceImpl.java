package ru.otus.gromov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.repository.GenreRepository;

import java.util.Objects;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Mono<Genre> save(Genre genre) {
        return repository.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Genre> getById(String id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Genre> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Mono<Void> delete(String id) {
        return repository.delete(Objects.requireNonNull(repository.findById(id).block()));
    }
}
