package com.affinitas.chat.messages.controller;

import javax.validation.Valid;

import com.affinitas.chat.messages.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.affinitas.chat.messages.data.Message;
import com.affinitas.chat.users.data.User;

@RestController
public class MessageController {
	public static final String MESSAGES_URL = "/messages";
	public static final String LAST_MESSAGE_URL = "/messages/user";
	@Autowired
	MessageService messageService;

	@PostMapping(MESSAGES_URL)
    public ResponseEntity<?> sendMessage(
            @Valid @RequestBody Message message, Errors errors) {
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors() || message == null) {
            return ResponseEntity.badRequest().body("error");
        }

        messageService.addMessage(message);
        return ResponseEntity.ok("ok");
    }

	/**
	 * This method is needed for pull methodology. it gets the last message for a specific user.
	 * @param userId
	 * @return last message for user with userId
	 */
	@GetMapping(LAST_MESSAGE_URL + "/{userId}")
    public ResponseEntity<?> getUserLastMessage(
    		@PathVariable("userId") Integer userId) {

		Message message = messageService.getUserLastMessage(userId);
		if(message == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(message);
    }
}
