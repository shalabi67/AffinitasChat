package com.affinitas.chat.messages.controller;

import com.affinitas.chat.messages.data.Message;
import com.affinitas.chat.users.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by mohammad on 2/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessageControllerTests {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetLastMessage() throws Exception{
        mockMvc.perform(get(getUrl(-1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCompleteConversation() throws Exception {
        String body = "Hello";
        sendMessage(body, 1, 2);
        getMessage(1, body);

        sendMessage(body, 2, 1);
        getMessage(2, body);

        body = "How are you?";
        sendMessage(body, 1, 2);
        getMessage(1, body);

        body = "Good, how are you?";
        sendMessage(body, 2, 1);
        getMessage(2, body);
    }

    @Test
    public void testSendMessage() throws Exception {
        String body = "This is a test from user 2 to user 1";
        sendMessage(body, 1, 2);
        getMessage(1, body);
    }
    private Message createMessage(String message, int toUser, int fromUser) {
        Message m = new Message();
        m.setMessage(message);
        m.setToUser(userService.getUser(toUser));
        m.setFromUser(userService.getUser(fromUser));

        return m;
    }

    private void sendMessage(String message, int toUser, int fromUser) throws Exception {
        String messageJson = json(createMessage(message, toUser, fromUser));
        mockMvc.perform(post(MessageController.MESSAGES_URL)
                .content(messageJson)
                .contentType(contentType))
                .andExpect(status().isOk());
    }
    private String getUrl(int id) {
        String url = MessageController.LAST_MESSAGE_URL + "/" + id;
        return url;
    }
    private void getMessage(int id, String expectedMessage) throws Exception {
        mockMvc.perform(get(getUrl(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(expectedMessage)));;
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
