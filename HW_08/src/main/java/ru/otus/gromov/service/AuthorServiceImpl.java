package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.repository.AuthorRepository;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return repository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Author with id = " + id + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Author getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Author with name: " + name + " not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundException("Author with id = " + id + " not found!")));
    }
}
