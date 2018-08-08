package ru.otus.gromov.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

@Transactional
public class AuthorRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    protected AuthorRepository repository;

    @Test
    public void count() {
        Assert.assertEquals(3, repository.count());
    }

    @Test
    public void save() {
        Author authorTest = repository.save(new Author(0L, "TestTest"));
        Assert.assertEquals(
                authorTest,
                repository.findByName(authorTest.getName())
                        .orElseThrow(() -> new NotFoundException("Author not found!")));
    }

    @Test
    public void findById() {
        Author authorExpected = new Author(1L, "Joshua Bloch");

        Assert.assertEquals(
                authorExpected,
                repository.findById(authorExpected.getId())
                        .orElseThrow(() -> new NotFoundException("Author not found!")));
    }

    @Test
    public void findAll() {
        List<Author> ExpectedList = Arrays.asList(
                new Author(2L, "Brian Goetz"),
                new Author(3L, "Doug Lea"),
                new Author(1L, "Joshua Bloch"));

        Assert.assertEquals(ExpectedList,
                repository.findAll(new Sort(Sort.Direction.ASC, "name")));
    }

    @Test
    public void findByName() {
        Author authorExpected = new Author(1L, "Joshua Bloch");

        Assert.assertEquals(
                authorExpected,
                repository.findByName(authorExpected.getName())
                        .orElseThrow(() -> new NotFoundException("Author not found!")));
    }

    @Test
    public void delete() {
        Author authorExpected = repository.findById(1L).orElseThrow(() -> new NotFoundException("Author not found!"));
        repository.delete(authorExpected);

        thrown.expect(NotFoundException.class);
        repository.findById(authorExpected.getId()).orElseThrow(() -> new NotFoundException("Author not found!"));
    }
}