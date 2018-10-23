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
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.service.MapperService;
import ru.otus.gromov.util.exception.NotFoundException;

import javax.annotation.PostConstruct;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class AuthorRestControllerTest {

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

    private static final String REST_URL = "/api/authors/";

    @Autowired
    protected AuthorService service;

    @Autowired
    protected MapperService mapperService;

    @Test
    public void testGet() throws Exception {
        Long id = 1L;
        mockMvc.perform(get(REST_URL + id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mapperService.contentJson(service.getById(id)));
    }

    @Test(expected = NotFoundException.class)
    public void testDelete() throws Exception {
        Long id = 4L;
        mockMvc.perform(delete(REST_URL + id))
                .andExpect(status().isNoContent());
        service.getById(id);
    }

    @Test
    public void testCreate() throws Exception {
        Author created = new Author("TESTTEST");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapperService.writeValue(created)));
        Author returned = mapperService.readFromJson(action, Author.class);
        created.setId(returned.getId());
        Assert.assertEquals(created, returned);
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = 4L;
        Author updated = service.getById(id);
        updated.setName("New Name");
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapperService.writeValue(updated)))
                .andExpect(status().isOk());
        Assert.assertEquals(service.getById(id), updated);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mapperService.contentJson(service.getAll()));
    }
}