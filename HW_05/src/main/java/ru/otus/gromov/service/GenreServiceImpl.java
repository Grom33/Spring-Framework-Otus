package ru.otus.gromov.service;

import org.springframework.stereotype.Service;
import ru.otus.gromov.dao.GenreDao;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao repository;

    public GenreServiceImpl(GenreDao repository) {
        this.repository = repository;
    }

    @Override
    public int count() {
        return repository.count();
    }

    @Override
    public Genre insert(Genre genre) {
        Genre genreExist = getByName(genre.getName());
        if (genreExist == null) {
            repository.insert(genre);
            genreExist = getByName(genre.getName());
        }
        return genreExist;
    }

    @Override
    public Genre getById(int id) {
        Genre genre = repository.getById(id);
        if (genre == null) throw new NotFoundException("Genre with id = " + id + " not found!");
        return genre;
    }

    @Override
    public Genre getByName(String name) {
        Genre genre = repository.getByName(name);
        if (genre == null) throw new NotFoundException("Genre with name = " + name + " not found!");
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Genre> getbyArrayID(Integer[] arrayOfId) {
        return repository.getByArrayID(arrayOfId);
    }
}
