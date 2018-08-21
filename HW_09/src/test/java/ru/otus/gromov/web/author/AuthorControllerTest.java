package ru.otus.gromov.web.author;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.service.AuthorService;
import ru.otus.gromov.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AuthorControllerTest extends AbstractControllerTest {

    @Autowired
    protected AuthorService service;

    @Test
    public void listAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("authors", service.getAll()));
    }

    @Test
    public void getByid() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/author/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("author/author"))
                .andExpect(model().attribute("author", service.getById(id)));
    }

    @Test
    public void previewAuthor() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/author/edit/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("author/edit_author"))
                .andExpect(model().attribute("author", service.getById(id)));

    }

    @Test
    public void saveAuthor() throws Exception {
        Long id = 1L;
        Author author = service.getById(id);
        String newName = "NEW NAME";

        author.setName(newName);

        mockMvc.perform(post("/author/edit/" + id)
                .param("name", newName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("author/author"))
                .andExpect(model().attribute("author", service.getById(id)));
    }
}