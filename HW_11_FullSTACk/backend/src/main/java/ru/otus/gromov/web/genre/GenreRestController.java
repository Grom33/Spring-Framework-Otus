package ru.otus.gromov.web.genre;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Genre;

@RestController
public class GenreRestController extends AbstractGenreController {

    @GetMapping(value = "/api/genres/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Genre> get(@PathVariable String id) {
        return super.get(id);
    }

    @DeleteMapping("/api/genres/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return super.delete(id);
    }

    @PostMapping(value = "/api/genres/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Genre> create(@RequestBody Genre genre) {
        return super.create(genre);
    }

    @PutMapping(value = "/api/genres/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Genre> update(@RequestBody Genre genre) {
        return super.update(genre);
    }

    @GetMapping(value = "/api/genres/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Genre> getAll() {
        return super.getAll();
    }
}
