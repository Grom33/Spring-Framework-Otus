package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.gromov.domain.Soul;

@Repository
public interface HellRepository extends JpaRepository<Soul, Long> {
}
