package ru.otus.gromov.web.author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.service.AuthorService;
import reactor.core.publisher.Flux;

public abstract class AbstractAuthorController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthorService authorService;

    public Mono<Author> create(Author author) {
        log.info("create author {}", author);
        return authorService.save(author);
    }

    public Mono<Author> get(String id) {
        log.info("get author {}", id);
        return authorService.getById(id);
    }

    public Mono<Author> update(Author author) {
        log.info("create author {}", author.getId());
        return authorService.save(author);
    }

    public Mono<Void> delete(String id) {
        log.info("delete author {}", id);
        return authorService.delete(id);
    }

    public Flux<Author> getAll() {
        log.info("get all authors");
        return authorService.getAll();
    }

}
