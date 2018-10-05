package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gromov.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}