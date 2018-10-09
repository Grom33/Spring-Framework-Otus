package ru.otus.gromov.web.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.otus.gromov.service.BookServiceImpl;

import javax.annotation.PostConstruct;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookServiceImpl.class)
public class SecurityBookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(
            username = "admin",
            authorities = "ROLE_ADMIN"
    )
    @Test
    public void testPublic() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}
