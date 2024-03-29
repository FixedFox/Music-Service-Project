package ru.fixedfox.musicservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.fixedfox.musicservice.services.UserDetailsServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @MockBean
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLandingPage_succesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    void getStartPage_without_authorization_fail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mainpage"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/sign_in"));
    }

    @Test
    void getSubscriptionPage_without_authorization_fail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/subscriptions"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/sign_in"));
    }
}