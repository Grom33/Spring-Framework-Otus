package ru.otus.gromov.service.nosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import ru.otus.gromov.domain.Author;

@Service
public class AuthorServiceMongo {

    private MongoOperations mongoOperations;

    @Autowired
    public AuthorServiceMongo(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }


    public void insert(Author author) {
        mongoOperations.insert(author);
    }

    public void get() {
        System.out.println(mongoOperations.findAll(Author.class));
    }

}
