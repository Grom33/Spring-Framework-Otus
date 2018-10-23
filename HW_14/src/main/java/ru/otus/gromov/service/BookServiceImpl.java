package ru.otus.gromov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.repository.BookRepository;
import ru.otus.gromov.repository.CommentRepository;
import ru.otus.gromov.util.exception.NotFoundException;

import java.util.List;

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
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Book with id =" + id + " not found!"));
    }


    @Override
    @Transactional(readOnly = true)
    public Book getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Book with name: " + name + " not found!"));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return repository.findAll();
    }


    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundException("Book with id = " + id + " not found!")));
    }


    @Override
    public void addComment(String text, Long bookId) {
        Comment comment = new Comment(text,
                repository.findById(bookId)
                        .orElseThrow(() -> new NotFoundException("Book with id = " + bookId + " not found!"))
                        .getId());
        commentRepository.save(comment);
    }


    @Override
    public void deleteComment(Long commentId) {
        commentRepository.delete(commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment with id = " + commentId + " not found!")));
    }

    @Override
    public List<Comment> getComments(Long bookId) {
        return commentRepository.findAllByBookId(repository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with id = " + bookId + " not found!"))
                .getId());
    }
}
