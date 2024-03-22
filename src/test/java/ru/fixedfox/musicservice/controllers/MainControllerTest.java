package ru.fixedfox.musicservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MainControllerTest {

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    WebApplicationContext context;
    @Test
    void getLandingPage_succesTest() {
    }

    @Test
    void getStartPage() {
    }

    @Test
    void getSubscriptionPage() {
    }

    @Test
    void removeCreatorFromUserSubscriptions() {
    }
}