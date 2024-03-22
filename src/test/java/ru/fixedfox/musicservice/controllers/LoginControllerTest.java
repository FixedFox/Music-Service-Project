package ru.fixedfox.musicservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fixedfox.musicservice.configurations.WebSecurityConfiguration;
import ru.fixedfox.musicservice.repository.*;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(LoginController.class)
@Import(WebSecurityConfiguration.class)
class LoginControllerTest {

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    AuthorityRepository authorityRepository;

    @MockBean
    CreatorRepository creatorRepository;

    @MockBean
    GenreRepository genreRepository;

    @MockBean
    TracklistRepository tracklistRepository;

    @MockBean
    TracklistTypeRepository tracklistTypeRepository;

    @MockBean
    TrackRepository trackRepository;

    @MockBean
    UserRepository userRepository;
    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void getLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sign_in"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign_in"));
    }

    @Test
    void getRegistrationPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sign_up"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign_up"));
    }

    @Test
    void registerNewUser_succesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sign_up")
                        .param("username","fox").param("password", "123")
                        .param("name", "Name"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/sign_in"));
    }
}