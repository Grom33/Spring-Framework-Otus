package ru.otus.gromov.web;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResourcesTest extends AbstractControllerTest {

    @Test
    public void testResourcesCSS() throws Exception {
        mockMvc.perform(get("/css/bootstrap.min.css"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("text/css")))
                .andExpect(status().isOk());
    }

    @Test
    public void testResourcesJS() throws Exception {
        mockMvc.perform(get("/js/bootstrap.min.js"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("application/javascript")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/js/jquery-3.3.1.slim.min.js"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("application/javascript")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/js/popper.min.js"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("application/javascript")))
                .andExpect(status().isOk());
    }

}
