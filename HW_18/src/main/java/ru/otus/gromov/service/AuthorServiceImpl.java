package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.otus.gromov.bulkhead.DBCommand;
import ru.otus.gromov.dao.AuthorDao;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.util.exception.EntityNotFound;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@SuppressWarnings("Unchecked")
@Service
public class AuthorServiceImpl implements AuthorService {
	private final AuthorDao repository;

	@Autowired
	public AuthorServiceImpl(AuthorDao repository) {
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
	public Author insert(Author author) {
		return (Author) getCommand()
				.setRepositoryMethod(
						(aut) -> {
							repository.insert((Author) aut);
							return null;
						}, author,
						repository)
				.execute();
	}

	@Override
	public Author getById(Long id) {
		try {
			return (Author) getCommand()
					.setRepositoryMethod(
							(idA) -> repository.getById((long) idA),
							id, repository)
					.execute();
		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}

	}

	@Override
	public Author getByName(String name) {
		try {
			return (Author) getCommand()
					.setRepositoryMethod(
							(nameA) -> repository.getByName((String) nameA),
							name, repository)
					.execute();
		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Author> getAll() {
		return (List<Author>) getCommand()
				.setRepositoryMethodWithoutParametrs(
						(Void) -> repository.getAll(),
						repository)
				.execute();
	}

	@Override
	public void delete(Long id) {
		getCommand()
				.setRepositoryMethod(
						(idx) -> {
							repository.delete((Long) idx);
							return null;
						}, id, repository)
				.execute();

	}

	@Lookup
	@Bean()
	public  DBCommand getCommand() {
		return null;
	}
}
