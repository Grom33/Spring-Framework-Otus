package ru.otus.gromov.dao;

import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.EntityNotFound;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository implements GenreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return entityManager.createNamedQuery(Genre.GET_COUNT_NAMED_QUERY, Long.class)
                .getSingleResult();
    }

    @Override
    public Genre insert(Genre genre) {
        if (genre.isNew()) {
            entityManager.persist(genre);
        } else {
            return entityManager.merge(genre);
        }
        return genre;
    }

    @Override
    public Genre getById(Long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id))
                .orElseThrow(() -> new EntityNotFound("Genre with id = " + id + " not found!"));
    }

    @Override
    public Genre getByName(String name) {
        try {
            return entityManager.createNamedQuery(Genre.GET_BY_NAME_NAMED_QUERY, Genre.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFound("Genre with name = " + name + " not found!");
        }
    }

    @Override
    public List<Genre> getAll() {
        return entityManager.createNamedQuery(Genre.GET_ALL_NAMED_QUERY, Genre.class)
                .getResultList();
    }

    @Override
    public boolean delete(Genre genre) {
        entityManager.remove(getById(genre.getId()));
        return true;
    }
}
