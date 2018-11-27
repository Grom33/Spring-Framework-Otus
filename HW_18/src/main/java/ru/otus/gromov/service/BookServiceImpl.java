package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.bulkhead.DBCommand;
import ru.otus.gromov.dao.BookDao;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.util.exception.EntityNotFound;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
	private final BookDao repository;

	@Autowired
	public BookServiceImpl(BookDao repository) {
		this.repository = repository;
	}

	@Override

	public long count() {
		return (long) getCommand()
				.setRepositoryMethodWithoutParametrs(
						(Void) -> repository.count(), repository)
				.execute();

	}

	@Override
	public void insert(Book book) {
		getCommand()
				.setRepositoryMethod((bookE) -> {
					repository.insert((Book) bookE);
					return null;
				}, book, repository)
				.execute();

	}

	@Override
	public Book getById(Long id) {
		try {
			return (Book) getCommand()
					.setRepositoryMethod(
							(bookId) -> repository.getById((Long) bookId), id, repository)
					.execute();

		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override

	public Book getByName(String name) {
		try {
			return (Book) getCommand()
					.setRepositoryMethod(
							(bookName) -> repository.getByName((String) bookName), name, repository)
					.execute();
		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override

	public List<Book> getAll() {
		return (List<Book>) getCommand()
				.setRepositoryMethodWithoutParametrs(
						(Void) -> repository.getAll(),
						repository)
				.execute();
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		try {
			getCommand()
					.setRepositoryMethod(
							(Void) -> {
								repository.delete(repository.getById(id));
								return null;
							}, id, repository)
					.execute();
		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Lookup
	@Bean()
	public  DBCommand getCommand() {
		return null;
	}

}
