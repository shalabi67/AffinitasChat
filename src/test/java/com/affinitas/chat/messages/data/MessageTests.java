package com.affinitas.chat.messages.data;

import com.affinitas.chat.users.data.UsersRepository;
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
public class MessageTests {
    @Autowired
    UsersRepository usersRepository;

    @Test
    public void testCompare() {
        Message message = new Message();
        initMessage(message);
        Assert.isTrue(message.equals(message));

        Message m1 = new Message();
        initMessage(m1);
        Assert.isTrue(message.equals(m1));

        m1.setMessageId(18);
        Assert.isTrue(!message.equals(m1));

        m1.setMessage("111111111");
        m1.setMessageId(message.getMessageId());
        Assert.isTrue(!message.equals(m1));

        m1.setMessage(message.getMessage());
        m1.setToUser(message.getFromUser());
        Assert.isTrue(!message.equals(m1));

        m1.setToUser(message.getToUser());
        m1.setFromUser(message.getToUser());
        Assert.isTrue(!message.equals(m1));


    }

    private void initMessage(Message message) {
        message.setFromUser(usersRepository.getUserById(1));
        message.setToUser(usersRepository.getUserById(2));
        message.setMessage("");
        message.setMessageId(1);
    }
}
