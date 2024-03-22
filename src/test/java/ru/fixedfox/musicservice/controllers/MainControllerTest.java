package ru.fixedfox.musicservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.fixedfox.musicservice.configurations.WebSecurityConfiguration;
import ru.fixedfox.musicservice.entity.User;
import ru.fixedfox.musicservice.repository.*;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;


@WebMvcTest(MainController.class)
@Import(WebSecurityConfiguration.class)
class MainControllerTest {

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
    void getLandingPage_succesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}