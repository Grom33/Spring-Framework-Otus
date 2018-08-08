package ru.otus.gromov.dao;

import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.util.exception.EntityNotFound;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createNamedQuery(Book.GET_COUNT_NAMED_QUERY, Long.class)
                .getSingleResult();
    }

    @Override
    public Book insert(Book book) {
        if (book.isNew()) {
            entityManager.persist(book);
        } else {
            return entityManager.merge(book);
        }
        return book;
    }

    @Override
    public Book getById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id))
                .orElseThrow(() -> new EntityNotFound("Book with id = " + id + " not found!"));
    }

    @Override
    public Book getByName(String name) {
        try {
            return entityManager.createNamedQuery(Book.GET_BY_NAME_NAMED_QUERY, Book.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFound("Book with name = " + name + " not found!");
        }
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createNamedQuery(Book.GET_ALL_NAMED_QUERY, Book.class)
                .getResultList();
    }

    @Override
    public boolean delete(Book book) {
        entityManager.remove(getById(book.getId()));
        return true;
    }
}
