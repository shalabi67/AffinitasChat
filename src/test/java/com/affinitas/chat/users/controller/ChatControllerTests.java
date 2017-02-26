package com.affinitas.chat.users.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by mohammad on 2/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ChatControllerTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testUser1() throws Exception{
        mockMvc.perform(get("/user1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUser2() throws Exception{
        mockMvc.perform(get("/user2"))
                .andExpect(status().isOk());
    }
}
