package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.gromov.dao.AuthorDao;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao repository;

    @Autowired
    public AuthorServiceImpl(AuthorDao repository) {
        this.repository = repository;
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public Author insert(Author author) {
        Author authorExist = getByName(author.getName());
        if (authorExist == null) {
            repository.insert(author);
            authorExist = getByName(author.getName());
        }
        return authorExist;
    }

    @Override
    public Author getById(int id) {
        Author author = repository.getById(id);
        if (author == null) throw new NotFoundException("Author with id = " + id + " not found!");
        return author;
    }

    @Override
    public List<Author> getAll() {
        return repository.getAll();
    }

    @Override
    public Author getByName(String name) {
        Author author = repository.getByName(name);
        if (author == null) throw new NotFoundException("Author with name = " + name + " not found!");
        return author;
    }

    @Override
    public List<Author> getbyArrayID(Integer[] arrayOfId) {
        return repository.getByArrayID(arrayOfId);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
