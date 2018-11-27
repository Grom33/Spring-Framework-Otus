package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
public class AuthorServiceImplTest extends AbstractServiceTest {
    @Autowired
    protected AuthorService service;

    @Test
    public void count() {
        Assert.assertEquals(3, service.count());
        System.out.println(service.count());
        System.out.println(service.count());
    }

    @Test
    public void insert() {
        Author authorTest = new Author(0L, "TestTest");
        service.insert(authorTest);
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
        List<Author> ExpectedList = new ArrayList<>(
                Arrays.asList(
                        new Author(1L, "Joshua Bloch"),
                        new Author(2L, "Brian Goetz"),
                        new Author(3L, "Doug Lea")
                ));
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
       // Author authorExpected = service.getById(1L);
        service.delete(1L);
        //thrown.expect(NotFoundException.class);
        //service.getById(authorExpected.getId());
    }
}