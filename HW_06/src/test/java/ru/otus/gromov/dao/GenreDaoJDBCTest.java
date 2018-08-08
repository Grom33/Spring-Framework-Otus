package ru.otus.gromov.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.EntityNotFound;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Transactional
public class GenreDaoJDBCTest extends AbstractDaoTest {

    @Autowired
    protected GenreDao repository;

    @Test
    public void count() {
        Assert.assertEquals(3L, repository.count());
    }

    @Test
    public void insert() {
        Genre genreTest = new Genre(0L, "TestTest");
        repository.insert(genreTest);

        Assert.assertEquals(
                genreTest,
                repository.getByName(genreTest.getName()));
    }

    @Test
    public void getById() {
        Genre genreExpected = new Genre(1L, "Patterns");
        Genre genreActual = repository.getById(genreExpected.getId());
        assertThat(repository.getById(genreExpected.getId()))
                .isEqualToIgnoringGivenFields(genreActual, "version", "books");
    }

    @Test
    public void getAll() {
        List<Genre> ExpectedList = new ArrayList<>(
                Arrays.asList(
                        new Genre(1L, "Patterns"),
                        new Genre(2L, "Java"),
                        new Genre(3L, "Concurrency")
                ));

        assertThat(repository.getAll())
                .usingElementComparatorIgnoringFields("version", "books")
                .isEqualTo(ExpectedList);
    }

    @Test
    public void getByName() {
        Genre genreExpected = new Genre(1L, "Patterns");

        assertThat(repository.getByName(genreExpected.getName()))
                .isEqualToIgnoringGivenFields(genreExpected, "version", "books");
    }

    @Test
    public void delete() {
        Genre genreExpected = repository.getById(1L);
        Assert.assertTrue(repository.delete(genreExpected));

        thrown.expect(EntityNotFound.class);
        repository.getById(genreExpected.getId());
    }

}