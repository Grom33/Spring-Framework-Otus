package ru.otus.gromov.web.author;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.otus.gromov.TestUtil;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.util.JsonUtil;
import ru.otus.gromov.util.exception.NotFoundException;
import ru.otus.gromov.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.gromov.TestUtil.*;

public class AuthorRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/api/authors/";

    @Autowired
    protected AuthorService service;

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
                .content(JsonUtil.writeValue(created)));
        Author returned = readFromJson(action, Author.class);
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
}