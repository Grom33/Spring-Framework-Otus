package ru.otus.gromov.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.TestData;


public class BookDaoJDBCTest extends AbstractDaoTest {
    @Autowired
    protected BookDao repository;

    @Test
    public void count() {
        Assert.assertEquals(3, repository.count());
    }

    @Test
    public void insert() {
        Assert.assertNull(repository.getByName(TestData.TEST_INSERTED_BOOK_WITH_ID_4.getName()));
        repository.insert(TestData.TEST_INSERTED_BOOK_WITH_ID_4);
        Assert.assertEquals(
                TestData.TEST_INSERTED_BOOK_WITH_ID_4,
                repository.getByName(TestData.TEST_INSERTED_BOOK_WITH_ID_4.getName()));

    }

    @Test
    public void update() {
        Assert.assertEquals(
                TestData.TEST_BOOK_WITH_ID_2,
                repository.getById(TestData.TEST_BOOK_WITH_ID_2.getId()));

        Assert.assertNotEquals(
                TestData.TEST_UPDATETD_BOOK_WITH_ID_2,
                repository.getById(TestData.TEST_UPDATETD_BOOK_WITH_ID_2.getId()));

        repository.update(TestData.TEST_UPDATETD_BOOK_WITH_ID_2);

        Assert.assertEquals(
                TestData.TEST_UPDATETD_BOOK_WITH_ID_2,
                repository.getById(TestData.TEST_UPDATETD_BOOK_WITH_ID_2.getId()));

        Assert.assertNotEquals(
                TestData.TEST_BOOK_WITH_ID_2,
                repository.getById(TestData.TEST_BOOK_WITH_ID_2.getId()));
    }

    @Test
    public void getById() {
        Assert.assertEquals(TestData.TEST_BOOK_WITH_ID_2,
                repository.getById(TestData.TEST_BOOK_WITH_ID_2.getId()));
    }

    @Test
    public void getByName() {
        Assert.assertEquals(
                TestData.TEST_BOOK_WITH_ID_2,
                repository.getByName(TestData.TEST_BOOK_WITH_ID_2.getName()));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(TestData.LIST_WITH_ALL_BOOKS, repository.getAll());
    }

    @Test
    public void delete() {
        Assert.assertNotNull(repository.getById(TestData.TEST_BOOK_WITH_ID_2.getId()));
        repository.delete(TestData.TEST_BOOK_WITH_ID_2.getId());
        Assert.assertNull(repository.getById(TestData.TEST_BOOK_WITH_ID_2.getId()));
    }
}