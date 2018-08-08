package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gromov.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    long count();

    Genre save(Genre author);

    Optional<Genre> findById(Long id);

    Optional<Genre> findByName(String name);

    List<Genre> findAll();

    void delete(Genre genre);

}
