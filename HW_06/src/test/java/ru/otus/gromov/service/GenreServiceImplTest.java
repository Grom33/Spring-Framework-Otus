package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
public class GenreServiceImplTest extends AbstractServiceTest {
    @Autowired
    protected GenreService service;

    @Test
    public void count() {
        Assert.assertEquals(3L, service.count());
    }

    @Test
    public void insert() {
        Genre genreTest = new Genre(0L, "TestTest");
        service.insert(genreTest);

        Assert.assertEquals(
                genreTest,
                service.getByName(genreTest.getName()));
    }

    @Test
    public void getById() {
        Genre genreExpected = new Genre(1L, "Patterns");
        Assert.assertEquals(genreExpected,
                service.getById(genreExpected.getId()));
    }

    @Test
    public void getByName() {
        Genre genreExpected = new Genre(1L, "Patterns");
        Assert.assertEquals(genreExpected,
                service.getByName(genreExpected.getName()));
    }

    @Test
    public void getAll() {
        List<Genre> ExpectedList = new ArrayList<>(
                Arrays.asList(
                        new Genre(1L, "Patterns"),
                        new Genre(2L, "Java"),
                        new Genre(3L, "Concurrency")
                ));
        Assert.assertEquals(ExpectedList, service.getAll());
    }


    public void delete() {
        Genre genreExpected = service.getById(1L);

        service.delete(genreExpected.getId());
        thrown.expect(NotFoundException.class);
        service.getById(genreExpected.getId());
    }

}