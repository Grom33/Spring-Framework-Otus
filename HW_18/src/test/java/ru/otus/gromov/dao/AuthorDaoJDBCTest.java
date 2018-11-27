package ru.otus.gromov.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.EntityNotFound;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
public class AuthorDaoJDBCTest extends AbstractDaoTest {

    @Autowired
    protected AuthorDao repository;

    @Test
    public void count() {
        Assert.assertEquals(3, repository.count());
    }

    @Test
    public void insert() {
        Author authorTest = new Author(0L, "TestTest");
        repository.insert(authorTest);
        Assert.assertEquals(
                authorTest,
                repository.getByName(authorTest.getName()));
    }

    @Test
    public void getById() {
        Author authorExpected = new Author(1L, "Joshua Bloch");
        assertThat(repository.getById(authorExpected.getId()))
                .isEqualToIgnoringGivenFields(authorExpected, "version", "books");
    }

    @Test
    public void getAll() {
        List<Author> ExpectedList = new ArrayList<>(
                Arrays.asList(
                        new Author(1L, "Joshua Bloch"),
                        new Author(2L, "Brian Goetz"),
                        new Author(3L, "Doug Lea")
                ));
        assertThat(repository.getAll())
                .usingElementComparatorIgnoringFields("version", "books")
                .isEqualTo(ExpectedList);
    }

    @Test
    public void getByName() {
        Author authorExpected = new Author(1L, "Joshua Bloch");
        assertThat(repository.getByName(authorExpected.getName()))
                .isEqualToIgnoringGivenFields(authorExpected, "version", "books");
    }

    @Test
    public void delete() {
        Author authorExpected = repository.getById(1L);
        repository.delete(1L);

        thrown.expect(EntityNotFound.class);
        repository.getById(authorExpected.getId());

    }

}