package com.affinitas.chat.messages.services;

import com.affinitas.chat.messages.data.Message;
import com.affinitas.chat.messages.data.MessagesRepository;
import com.affinitas.chat.notifications.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 2/25/2017.
 */
@Service
public class MessageService {
    @Autowired
    MessagesRepository messagesRepository;

    //TODO: for scalability reasons we need to use Notification controller.
    @Autowired
    NotificationService notificationService;

    public Message getUserLastMessage(int userId) {
        return messagesRepository.getUserLastMessage(userId);
    }

    public Message addMessage(Message message) {
        if(message == null) {
            return null;
        }

        //Notify other devices
        //TODO: this is an async call to notification rest controller for scalability reasons
        notificationService.notifyUserDevices(message.getToUser().getId(), message);

        return messagesRepository.addMessage(message);
    }
}
