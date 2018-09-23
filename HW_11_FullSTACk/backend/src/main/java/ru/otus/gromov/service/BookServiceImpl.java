package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.repository.BookRepository;
import ru.otus.gromov.repository.CommentRepository;

import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final CommentRepository commentRepository;

    @Autowired
    public BookServiceImpl(BookRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Long> count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Mono<Book> save(Book book) {
        return repository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Book> getById(String id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Book> getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Book> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Mono<Void> delete(String id) {
        return repository.delete(Objects.requireNonNull(repository.findById(id).block()));
    }

    @Override
    public Mono<Void> addComment(String text, String bookId) {
        Comment comment = new Comment(text,
                Objects.requireNonNull(repository.findById(bookId).block()).getId());
        return commentRepository.save(comment).then();
    }

    @Override
    public Mono<Void> deleteComment(String commentId) {
        return commentRepository.delete(Objects.requireNonNull(commentRepository.findById(commentId).block()));
    }

    @Override
    public Flux<Comment> getComments(String bookId) {
        return commentRepository.findAllByBookId(Objects.requireNonNull(repository.findById(bookId).block()).getId());
    }
}
