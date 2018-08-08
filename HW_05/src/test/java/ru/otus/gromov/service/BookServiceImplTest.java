package ru.otus.gromov.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.TestData;
import ru.otus.gromov.util.exception.NotFoundException;


public class BookServiceImplTest extends AbstractServiceTest {
    @Autowired
    protected BookService service;

    @Test
    public void count() {
        Assert.assertEquals(3, service.count());
    }

    @Test(expected = NotFoundException.class)
    public void insert() {
        service.getByName(TestData.TEST_INSERTED_BOOK_WITH_ID_4.getName());
        service.insert(TestData.TEST_INSERTED_BOOK_WITH_ID_4);
        Assert.assertEquals(
                TestData.TEST_INSERTED_BOOK_WITH_ID_4,
                service.getByName(TestData.TEST_INSERTED_BOOK_WITH_ID_4.getName()));
    }

    @Test
    public void getById() {
        Assert.assertEquals(TestData.TEST_BOOK_WITH_ID_1, service.getById(1));
    }

    @Test
    public void getByName() {
        Assert.assertEquals(
                TestData.TEST_BOOK_WITH_ID_2,
                service.getByName(TestData.TEST_BOOK_WITH_ID_2.getName()));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(TestData.LIST_WITH_ALL_BOOKS, service.getAll());
    }

    @Test
    public void getPrintableBook() {
        Assert.assertEquals(
                TestData.TEST_PRINTABLE_BOOK_WITH_ID_1,
                service.getPrintableBook(TestData.TEST_BOOK_WITH_ID_1));
    }

    @Test(expected = NotFoundException.class)
    public void delete() {
        Assert.assertEquals(TestData.TEST_BOOK_WITH_ID_1, service.getById(TestData.TEST_BOOK_WITH_ID_1.getId()));
        service.delete(TestData.TEST_BOOK_WITH_ID_1.getId());
        service.getById(TestData.TEST_BOOK_WITH_ID_1.getId());

    }
}