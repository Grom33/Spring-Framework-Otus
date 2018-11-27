package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.otus.gromov.bulkhead.DBCommand;
import ru.otus.gromov.dao.GenreDao;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.util.exception.EntityNotFound;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
	private final GenreDao repository;

	public GenreServiceImpl(GenreDao repository) {
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
	public Genre insert(Genre genre) {
		return (Genre) getCommand().setRepositoryMethod(
				(genreE) -> repository.insert((Genre) genreE), genre, repository)
				.execute();
	}

	@Override
	public Genre getById(Long id) {
		try {
			return (Genre) getCommand()
					.setRepositoryMethod(
							(genreId) -> repository.getById((Long) genreId), id, repository)
					.execute();
		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override
	public Genre getByName(String name) {
		try {
			return (Genre) getCommand()
					.setRepositoryMethod(
							(genreNAme) -> repository.getByName((String) genreNAme), name, repository)
					.execute();
		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Override
	public List<Genre> getAll() {
		return (List<Genre>) getCommand()
				.setRepositoryMethodWithoutParametrs(
						(Void) -> repository.getAll(), repository)
				.execute();
	}

	@Override
	public void delete(Long id) {
		try {
			getCommand()
					.setRepositoryMethod(
							(Void) -> {
								repository.delete(id);
								return null;
							}, id, repository)
					.execute();
		} catch (EntityNotFound e) {
			throw new NotFoundException(e.getMessage());
		}
	}

	@Lookup
	@Bean()
	public DBCommand getCommand() {
		return null;
	}

}
