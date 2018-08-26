package ru.otus.gromov.web.genre;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.util.JsonUtil;
import ru.otus.gromov.util.exception.NotFoundException;
import ru.otus.gromov.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.otus.gromov.TestUtil.contentJson;
import static ru.otus.gromov.TestUtil.readFromJson;

public class GenreRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/api/genres/";

    @Autowired
    protected GenreService service;

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
        Genre created = new Genre("TESTTEST");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));
        Genre returned = readFromJson(action, Genre.class);
        created.setId(returned.getId());
        Assert.assertEquals(created, returned);
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = 4L;
        Genre updated = service.getById(id);
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