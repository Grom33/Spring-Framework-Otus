package ru.otus.gromov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.gromov.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBookId(Long bookId);

    Comment findByReview(String review);
}
