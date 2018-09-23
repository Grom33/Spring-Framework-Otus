package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Mono<Author> save(Author author) {
        return repository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Author> getById(String id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Author> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Author> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Mono<Void> delete(String id) {
        return repository.delete(repository.findById(id).block());
    }
}
