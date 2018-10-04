package ru.otus.gromov.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void indexPage() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void listAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void viewAuthor() throws Exception {
        long id = 1L;
        mockMvc.perform(get("/authors/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void listBook() throws Exception {
        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void viewBook() throws Exception {
        long id = 1L;
        mockMvc.perform(get("/authors/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void listGenre() throws Exception {
        long id = 1L;
        mockMvc.perform(get("/genres"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void viewGenre() throws Exception {
        long id = 1L;
        mockMvc.perform(get("/genres/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }
}