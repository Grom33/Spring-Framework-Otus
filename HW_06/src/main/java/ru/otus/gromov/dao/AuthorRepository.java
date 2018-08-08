package ru.otus.gromov.dao;

import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.EntityNotFound;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createNamedQuery(Author.GET_COUNT_NAMED_QUERY, Long.class)
                .getSingleResult();
    }

    @Override
    public Author insert(Author author) {
        if (author.isNew()) {
            entityManager.persist(author);
        } else {
            return entityManager.merge(author);
        }
        return author;
    }

    @Override
    public Author getById(Long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id))
                .orElseThrow(() -> new EntityNotFound("Author with id = " + id + " not found!"));
    }

    @Override
    public Author getByName(String name) {
        try {
            return entityManager.createNamedQuery(Author.GET_BY_NAME_NAMED_QUERY, Author.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFound("Author with name = " + name + " not found!");
        }
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createNamedQuery(Author.GET_ALL_NAMED_QUERY, Author.class)
                .getResultList();
    }

    @Override
    public boolean delete(Author author) {
        entityManager.remove(getById(author.getId()));
        return true;
    }
}
