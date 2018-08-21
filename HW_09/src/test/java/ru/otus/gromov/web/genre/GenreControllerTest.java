package ru.otus.gromov.web.genre;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.GenreService;
import ru.otus.gromov.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class GenreControllerTest extends AbstractControllerTest {

    @Autowired
    protected GenreService service;

    @Test
    public void listGenre() throws Exception {
        mockMvc.perform(get("/genres"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("genre/genres"))
                .andExpect(model().attribute("genres", service.getAll()));
    }

    @Test
    public void getByid() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/genre/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("genre/genre"))
                .andExpect(model().attribute("genre", service.getById(id)));
    }

    @Test
    public void previewAuthor() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/genre/edit/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("genre/edit_genre"))
                .andExpect(model().attribute("genre", service.getById(id)));
    }

    @Test
    public void saveAuthor() throws Exception {
        Long id = 1L;
        Genre genre = service.getById(id);
        String newName = "NEW NAME";

        genre.setName(newName);

        mockMvc.perform(post("/genre/edit/" + id)
                .param("name", newName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("genre/genre"))
                .andExpect(model().attribute("genre", service.getById(id)));
    }
}