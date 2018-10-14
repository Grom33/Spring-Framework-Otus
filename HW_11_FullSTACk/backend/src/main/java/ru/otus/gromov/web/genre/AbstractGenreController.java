package ru.otus.gromov.web.genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.GenreService;

@RestController
public abstract class AbstractGenreController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService genreService;

    public Mono<Genre> create(Genre genre) {
        log.info("create genre {}", genre);
        return genreService.save(genre);
    }

    public Mono<Genre> get(String id) {
        log.info("get genre {}", id);
        return genreService.getById(id);
    }

    public Mono<Genre> update(Genre genre) {
        log.info("create genre {}", genre.getId());
        return genreService.save(genre);
    }

    public Mono<Void> delete(String id) {
        log.info("delete genre {}", id);
        return genreService.delete(id);
    }

    public Flux<Genre> getAll() {
        log.info("get all genres");
        return genreService.getAll();
    }
}
