package ru.otus.gromov.web.genre;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Genre;

import java.util.List;
@RestController
public class GenreRestController extends AbstractGenreController{

    @GetMapping(value = "/api/genres/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Genre get(@PathVariable Long id) {
        return  super.get(id);
    }

    @DeleteMapping("/api/genres/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }

    @PostMapping(value = "/api/genres/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Genre create(@RequestBody Genre genre){
        return super.create(genre);
    }
    @PutMapping(value = "/api/genres/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Genre genre){
        super.update(genre);
    }

    @GetMapping(value = "/api/genres/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Genre> getAll() {
        return super.getAll();
    }
}
