package ru.otus.gromov.web.book;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.repository.CommentRepository;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.BookService;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.web.AbstractControllerTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class BookControllerTest extends AbstractControllerTest {

    @Autowired
    protected BookService service;
    @Autowired
    protected AuthorService authorService;
    @Autowired
    protected GenreService genreService;
    @Autowired
    protected CommentRepository commentRepository;


    @Test
    public void listBook() throws Exception {
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", service.getAll()));
    }

    @Test
    public void getByid() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/book/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("book/book"))
                .andExpect(model().attribute("book", service.getById(id)))
                .andExpect(model().attribute("Comments", service.getComments(id)));
    }

    @Test
    public void previewBook() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/book/edit/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("book/edit_book"))
                .andExpect(model().attribute("book", service.getById(id)))
                .andExpect(model().attribute("genres", genreService.getAll()))
                .andExpect(model().attribute("authors", authorService.getAll()));
    }

    @Test
    public void editBook() throws Exception {
        Long id = 1L;
        Book book = service.getById(id);
        String newName = "NEW NAME";
        Set<Genre> newGenres = new HashSet<>(Arrays.asList(
                genreService.getById(2L),
                genreService.getById(3L)));
        book.setName(newName);
        book.setGenres(newGenres);

        String[] authors = book.getAuthors().stream().map(g -> String.valueOf(g.getId())).toArray(String[]::new);
        mockMvc.perform(post("/book/edit/" + id)
                .param("name", newName)
                .param("description", book.getDescription())
                .param("genres", "2", "3")
                .param("authors", authors))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("book/book"))
                .andExpect(model().attribute("book", service.getById(id)))
                .andExpect(model().attribute("genres", genreService.getAll()))
                .andExpect(model().attribute("authors", authorService.getAll()));
    }

    @Test
    public void addComment() throws Exception {
        Long id = 1L;
        Book book = service.getById(id);
        String comment = "TestTEST";
        Comment newComment = commentRepository.findByReview(comment);

        Assert.assertNull("Comment didn't insert", newComment);

        String[] authors = book.getAuthors().stream().map(g -> String.valueOf(g.getId())).toArray(String[]::new);
        mockMvc.perform(post("/comment/add/" + id)
                .param("review", comment))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("book/book"))
                .andExpect(model().attribute("book", service.getById(id)))
                .andExpect(model().attribute("Comments", service.getComments(id)));
        List<Comment> commentList = service.getComments(id);

        newComment = commentRepository.findByReview(comment);

        Assert.assertEquals("Comment inserted", newComment.getReview(), comment);

    }
}