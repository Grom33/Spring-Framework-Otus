package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Person;

@Repository
public interface NecrologueRepository extends JpaRepository<Person, Long> {
}
