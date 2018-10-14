package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

@Transactional
public class AuthorServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected AuthorService service;

    @Test
    public void count() {
        Assert.assertEquals(3, service.count());
    }

    @Test
    public void insert() {
        Author authorTest = service.save(new Author(0L, "TestTest"));

        Assert.assertEquals(
                authorTest,
                service.getByName(authorTest.getName()));
    }

    @Test
    public void getById() {
        Author authorExpected = new Author(1L, "Joshua Bloch");
        Assert.assertEquals(authorExpected, service.getById(1L));
    }

    @Test
    public void getAll() {
        List<Author> ExpectedList = Arrays.asList(
                service.getById(2L),
                service.getById(3L),
                service.getById(1L));

        Assert.assertEquals(ExpectedList, service.getAll());
    }

    @Test
    public void getByName() {
        Author authorExpected = new Author(1L, "Joshua Bloch");
        Assert.assertEquals(
                authorExpected,
                service.getByName(authorExpected.getName()));
    }

    @Test
    public void delete() {
        Author authorExpected = service.getById(1L);
        service.delete(authorExpected.getId());
        thrown.expect(NotFoundException.class);
        service.getById(authorExpected.getId());
    }
}