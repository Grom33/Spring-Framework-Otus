package ru.otus.gromov.web.author;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Author;

import java.util.List;
@RestController
public class AuthorRestController extends AbstractAuthorController{

    @GetMapping(value = "/api/authors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author get(@PathVariable Long id) {
        return  super.get(id);
    }

    @DeleteMapping("/api/authors/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }

    @PostMapping(value = "/api/authors/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author create(@RequestBody Author author){
        return super.create(author);
    }
    @PutMapping(value = "/api/authors/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Author author){
        super.update(author);
    }

    @GetMapping(value = "/api/authors/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getAll() {
        return super.getAll();
    }
}
