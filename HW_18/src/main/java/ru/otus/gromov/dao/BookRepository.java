package ru.otus.gromov.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

	@Transactional(readOnly = true)
	@Override
	public long count() {
		return entityManager.createNamedQuery(Book.GET_COUNT_NAMED_QUERY, Long.class)
				.getSingleResult();
	}

	@Override
	@Transactional()
	public Book insert(Book book) {
		if (book.isNew()) {
			entityManager.persist(book);
		} else {
			return entityManager.merge(book);
		}
		return book;
	}

	@Override
	@Transactional(readOnly = true)
	public Book getById(Long id) {
		return Optional.ofNullable(entityManager.find(Book.class, id))
				.orElseThrow(() -> new EntityNotFound("Book with id = " + id + " not found!"));
	}

	@Override
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	public List<Book> getAll() {
		return entityManager.createNamedQuery(Book.GET_ALL_NAMED_QUERY, Book.class)
				.getResultList();
	}

	@Override
	@Transactional()
	public void delete(Book book) {
		entityManager.remove(getById(book.getId()));
		entityManager.clear();
	}

}
