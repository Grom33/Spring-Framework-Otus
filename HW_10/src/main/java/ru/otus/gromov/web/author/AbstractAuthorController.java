package ru.otus.gromov.web.author;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.service.AuthorService;

import java.util.List;


public abstract class AbstractAuthorController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthorService authorService;

    public Author create(Author author){
        log.info("create author {}", author.getId());
        return authorService.save(author);
    }

    public Author get(Long id){
        log.info("get author {}", id);
        return  authorService.getById(id);
    }

    public void update (Author author){
        log.info("create author {}", author.getId());
        authorService.save(author);
    }

    public void delete(Long id){
        log.info("delete author {}", id);
        authorService.delete(id);
    }

    public List<Author> getAll(){
        log.info("get all authors");
        return  authorService.getAll();
    }

}
