package ru.otus.gromov.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.GenreService;

import java.util.List;

@RestController
public class GenreRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GenreService genreService;

    @Autowired
    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(value = "/api/genres/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Genre get(@PathVariable Long id) {
        log.info("get genre {}", id);
        return genreService.getById(id);
    }

    @DeleteMapping("/api/genres/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete genre {}", id);
        genreService.delete(id);
    }

    @PostMapping(value = "/api/genres/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Genre create(@RequestBody Genre genre) {
        log.info("create genre {}", genre.getId());
        return genreService.save(genre);
    }

    @PutMapping(value = "/api/genres/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Genre genre) {
        log.info("create genre {}", genre.getId());
        genreService.save(genre);
    }

    @GetMapping(value = "/api/genres/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Genre> getAll() {
        log.info("get all genres");
        return genreService.getAll();
    }
}
