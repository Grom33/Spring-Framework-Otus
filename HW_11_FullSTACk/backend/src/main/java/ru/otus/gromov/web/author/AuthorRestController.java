package ru.otus.gromov.web.author;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Author;

@RestController
public class AuthorRestController extends AbstractAuthorController {

    @GetMapping(value = "/api/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Author> get(@PathVariable String id) {
        return super.get(id);
    }

    @DeleteMapping("/api/authors/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return super.delete(id).then();
    }

    @PostMapping(value = "/api/authors/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Author> create(@RequestBody Author author) {
        return super.create(author);
    }

    @PutMapping(value = "/api/authors/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Author> update(@RequestBody Author author) {
        return super.update(author);
    }

    @GetMapping(value = "/api/authors/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Author> getAll() {
        return super.getAll();
    }
}
