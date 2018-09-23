package ru.otus.gromov.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Genre;

public interface GenreService {
    Mono<Long> count();

    Mono<Genre> save(Genre author);

    Mono<Genre> getById(String id);

    Mono<Genre> getByName(String name);

    Flux<Genre> getAll();

    Mono<Void> delete(String id);
}
