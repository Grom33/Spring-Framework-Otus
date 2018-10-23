package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gromov.domain.Soul;

public interface HeavenRepository extends JpaRepository<Soul, Long> {
}
