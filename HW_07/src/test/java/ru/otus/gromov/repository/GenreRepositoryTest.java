package ru.otus.gromov.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;


@Transactional
public class GenreRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected GenreRepository repository;

    @Test
    public void count() {
        Assert.assertEquals(3L, repository.count());
    }

    @Test
    public void save() {
        Genre genreTest = repository.save(new Genre(0L, "TestTest"));

        Assert.assertEquals(
                genreTest,
                repository.findByName(genreTest.getName())
                        .orElseThrow(() -> new NotFoundException("Genre not found!")));
    }

    @Test
    public void findById() {
        Genre genreExpected = new Genre(1L, "Patterns");

        Assert.assertEquals(
                genreExpected,
                repository.findById(genreExpected.getId())
                        .orElseThrow(() -> new NotFoundException("Genre not found!")));
    }

    @Test
    public void findAll() {
        List<Genre> ExpectedList = Arrays.asList(
                new Genre(1L, "Patterns"),
                new Genre(2L, "Java"),
                new Genre(3L, "Concurrency")
        );

        Assert.assertEquals(repository.findAll(), ExpectedList);
    }

    @Test
    public void findByName() {
        Genre genreExpected = new Genre(1L, "Patterns");

        Assert.assertEquals(
                genreExpected,
                repository.findByName(genreExpected.getName())
                        .orElseThrow(() -> new NotFoundException("Genre not found!")));
    }

    @Test
    public void delete() {
        Genre genreExpected = repository.findById(1L).orElseThrow(() -> new NotFoundException(""));
        repository.delete(genreExpected);

        thrown.expect(NotFoundException.class);
        repository.findById(genreExpected.getId()).orElseThrow(() -> new NotFoundException("Genre not found!"));
    }

}