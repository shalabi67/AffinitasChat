package com.affinitas.chat.messages.data;

import com.affinitas.chat.users.data.User;
import com.affinitas.chat.users.data.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

/**
 * Created by mohammad on 2/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessagesRepositoryAddTests {
    @Autowired
    MessagesRepository messagesRepository;

    @Autowired
    UsersRepository usersRepository;

    User toUser;
    User fromUser;
    Message message;
    @Before
    public void initTest() {
        toUser = User.create("testAddNewMessage", "test", "to");
        toUser = usersRepository.addUser(toUser);

        fromUser = User.create("testAddNewMessage", "test", "from");
        fromUser = usersRepository.addUser(fromUser);

        message = new Message();
        message.setMessage("This is a test");
        message.setFromUser(fromUser);
        message.setToUser(toUser);
    }

    @Test
    public void testAddNewMessage() {
        Message nullMessage = messagesRepository.getUserLastMessage(toUser.getId());
        Assert.isNull(nullMessage);  //tests assumes toUser is a new user and thus has no messages.
        messagesRepository.addMessage(message);

        Message lastMessage = messagesRepository.getUserLastMessage(toUser.getId());
        Assert.notNull(lastMessage);
        Assert.isTrue(lastMessage.equals(message));

        nullMessage = messagesRepository.getUserLastMessage(fromUser.getId());
        Assert.isNull(nullMessage);
    }

    @Test
    public void testAddMessage() {
        messagesRepository.addMessage(copy(message, 1));
        messagesRepository.addMessage(copy(message, 2));
        Message third = copy(message, 3);
        messagesRepository.addMessage(third);

        Message lastMessage = messagesRepository.getUserLastMessage(toUser.getId());
        Assert.notNull(lastMessage);
        Assert.isTrue(lastMessage.equals(third));
    }

    private Message copy(Message message, int count) {
        Message newMessage = new Message();
        newMessage.setFromUser(message.getFromUser());
        newMessage.setToUser(message.getToUser());
        newMessage.setMessage(message.getMessage() + count);

        return newMessage;

    }
}
