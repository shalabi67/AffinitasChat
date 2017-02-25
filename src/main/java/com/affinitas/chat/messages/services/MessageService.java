package com.affinitas.chat.messages.services;

import com.affinitas.chat.messages.data.Message;
import com.affinitas.chat.messages.data.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 2/25/2017.
 */
@Service
public class MessageService {
    @Autowired
    MessagesRepository messagesRepository;

    public Message getUserLastMessage(int userId) {
        return messagesRepository.getUserLastMessage(userId);
    }

    public Message addMessage(Message message) {
        if(message == null) {
            return null;
        }
        return messagesRepository.addMessage(message);
    }
}
