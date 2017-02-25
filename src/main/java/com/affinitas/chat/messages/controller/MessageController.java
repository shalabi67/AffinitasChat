package com.affinitas.chat.messages.controller;

import javax.validation.Valid;
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
	@PostMapping("/messages")
    public ResponseEntity<?> sendMessage(
            @Valid @RequestBody Message message, Errors errors) {
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body("error");
        }
        return ResponseEntity.ok("ok");
    }
	
	@GetMapping("/messages/user/{userId}")
    public ResponseEntity<?> getUserLastMessage(
    		@PathVariable("userId") Integer userId) {

		User user = new User();
		user.setId(1);
		user.setFirstName("Mohammad");
		user.setLastName("Shalabi");
		
		User user2 = new User();
		user2.setId(2);
		user2.setFirstName("Salem");
		user2.setLastName("Saeed");
		
		Message message = new Message();
		message.setMessage("This is a message");
		message.setFromUser(user);
		message.setToUser(user2);
		
        return ResponseEntity.ok(message);
    }
}
