package ru.fixedfox.musicservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
    }

    @Test
    void getLoginPage_succes_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sign_in"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign_in"));
    }

    @Test
    void getRegistrationPage_succes_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sign_up"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("sign_up"));
    }

    @Test
    void registerNewUser_succes_test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/sign_up")
                        .param("username", "username")
                        .param("password", "password")
                        .param("name", "name"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/sign_in"));
    }
}