package com.affinitas.chat.notifications.controller;

import com.affinitas.chat.messages.data.Message;
import com.affinitas.chat.notifications.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//TODO: removed temp
/**
 * Notify user devices about new message.
 * @author mohammad
 */
/*
@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @PostMapping("/notifications/{userId}")
    public ResponseEntity<?> sendMessage(
            @PathVariable("userId") Integer userId,
            @RequestBody Message message) {
        if(userId == null || message == null) {
            return ResponseEntity.noContent().build();
        }

        notificationService.notifyUserDevices(userId, message);

        return ResponseEntity.ok("ok");
    }
}
*/
