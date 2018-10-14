package ru.otus.gromov.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Author;

public interface AuthorService {
    Mono<Long> count();

    Mono<Author> save(Author author);

    Mono<Author> getById(String id);

    Flux<Author> getAll();

    Mono<Author> getByName(String name);

    Mono<Void> delete(String id);
}
