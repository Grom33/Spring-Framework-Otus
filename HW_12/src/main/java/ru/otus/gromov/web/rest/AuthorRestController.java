package ru.otus.gromov.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.service.AuthorService;

import java.util.List;

@RestController
public class AuthorRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/api/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author get(@PathVariable Long id) {
        log.info("get author {}", id);
        return authorService.getById(id);
    }

    @DeleteMapping("/api/authors/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("delete author {}", id);
        authorService.delete(id);
    }

    @PostMapping(value = "/api/authors/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author create(@RequestBody Author author) {
        log.info("create author {}", author.getId());
        return authorService.save(author);
    }

    @PutMapping(value = "/api/authors/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Author author) {
        log.info("create author {}", author.getId());
        authorService.save(author);
    }

    @GetMapping(value = "/api/authors/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getAll() {
        log.info("get all authors");
        return authorService.getAll();
    }
}
