package ru.otus.gromov.web.book;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.repository.CommentRepository;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.BookService;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.util.JsonUtil;
import ru.otus.gromov.util.exception.NotFoundException;
import ru.otus.gromov.web.AbstractControllerTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.gromov.TestUtil.contentJson;
import static ru.otus.gromov.TestUtil.readFromJson;

public class BookRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/books/";

    @Autowired
    protected BookService service;

    @Autowired
    protected GenreService genreService;

    @Autowired
    protected AuthorService authorService;

    @Autowired
    protected CommentRepository commentRepository;

    @Test
    public void testGet() throws Exception {
        Long id = 1L;
        mockMvc.perform(get(REST_URL + id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(service.getById(id)));
    }

    @Test(expected = NotFoundException.class)
    public void testDelete() throws Exception {
        Long id = 3L;
        mockMvc.perform(delete(REST_URL + id))
                .andExpect(status().isNoContent());
        service.getById(id);
    }

    @Test
    public void testCreate() throws Exception {
        Book created = new Book(0L, "Effective java 3rd edition", "description",
                new HashSet<Genre>(Arrays.asList(
                        genreService.getById(1L),
                        genreService.getById(2L))),
                new HashSet<Author>(Collections.singletonList(
                        authorService.getById(1L))));


        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));
        Book returned = readFromJson(action, Book.class);
        created.setId(returned.getId());
        Assert.assertEquals(created, returned);
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = 3L;
        Book updated = service.getById(id);
        updated.setName("New Name");
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        Assert.assertEquals(service.getById(id), updated);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(service.getAll()));
    }

    @Test
    public void getComments() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/api/books/"+id+"/comments/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(service.getComments(id)));
    }

    @Test
    public void addComments() throws Exception {
        Long id = 1L;
        Comment created = new Comment("NEW COMMENT", id);
        mockMvc.perform(post("/api/books/"+id+"/comments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isOk());
        Assert.assertNotNull(commentRepository.findByReview(created.getReview()));
    }

}