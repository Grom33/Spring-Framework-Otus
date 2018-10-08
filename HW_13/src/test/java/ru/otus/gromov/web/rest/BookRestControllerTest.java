package ru.otus.gromov.web.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.domain.Book;
import ru.otus.gromov.domain.Comment;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.repository.CommentRepository;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.BookService;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.service.MapperService;
import ru.otus.gromov.util.exception.NotFoundException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class BookRestControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

    @Autowired
    protected BookService service;

    @Autowired
    protected GenreService genreService;

    @Autowired
    protected AuthorService authorService;

    @Autowired
    protected CommentRepository commentRepository;

    @Autowired
    protected MapperService mapperService;

    @Test
    public void testGet() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/api/books/" + id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mapperService.contentJson(service.getById(id)));
    }

    @Test(expected = NotFoundException.class)
    public void testDelete() throws Exception {
        Long id = 3L;
        mockMvc.perform(delete("/api/books/" + id))
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


        ResultActions action = mockMvc.perform(post("/api/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapperService.writeValue(created)));
        Book returned = mapperService.readFromJson(action, Book.class);
        created.setId(returned.getId());
        Assert.assertEquals(created, returned);
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = 3L;
        Book updated = service.getById(id);
        updated.setName("New Name");
        mockMvc.perform(put("/api/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapperService.writeValue(updated)))
                .andExpect(status().isOk());
        Assert.assertEquals(service.getById(id), updated);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/api/books/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mapperService.contentJson(service.getAll()));
    }

    @Test
    public void getComments() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/api/books/" + id + "/comments/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mapperService.contentJson(service.getComments(id)));
    }

    @Test
    public void addComments() throws Exception {
        Long id = 1L;
        Comment created = new Comment("NEW COMMENT", id);
        mockMvc.perform(post("/api/books/" + id + "/comments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapperService.writeValue(created)))
                .andExpect(status().isOk());
        Assert.assertNotNull(commentRepository.findByReview(created.getReview()));
    }

}