package ru.otus.gromov.web.genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.GenreService;

import java.util.List;
@RestController
public abstract class AbstractGenreController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenreService genreService;

    public Genre create(Genre genre){
        log.info("create genre {}", genre.getId());
        return genreService.save(genre);
    }

    public Genre get(Long id){
        log.info("get genre {}", id);
        return  genreService.getById(id);
    }

    public void update (Genre genre){
        log.info("create genre {}", genre.getId());
        genreService.save(genre);
    }

    public void delete(Long id){
        log.info("delete genre {}", id);
        genreService.delete(id);
    }

    public List<Genre> getAll(){
        log.info("get all genres");
        return  genreService.getAll();
    }


}
