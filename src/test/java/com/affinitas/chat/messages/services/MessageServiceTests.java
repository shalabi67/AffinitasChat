package com.affinitas.chat.messages.services;

import com.affinitas.chat.messages.data.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

/**
 * Created by mohammad on 2/26/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessageServiceTests {
    @Autowired
    MessageService messageService;
    @Test
    public void testAddNullMessage() {
        Message message= messageService.addMessage(null);
        Assert.isNull(message);
    }
}
