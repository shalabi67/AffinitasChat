package com.affinitas.chat.notifications.services;

import com.affinitas.chat.messages.data.Message;
import com.affinitas.chat.users.data.Device;
import com.affinitas.chat.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mohammad on 2/26/2017.
 */
@Service
public class NotificationService {

    //TODO: we should use a call to the user controller for scalability
    //TODO: this should be an asyn call
    @Autowired
    UserService userService;
    public void notifyUserDevices(int userId, Message message) {
        List<Device> list = userService.getUserDevices(userId);
        for (Device device : list) {
            device.showNotification(message);
        }
    }
}
