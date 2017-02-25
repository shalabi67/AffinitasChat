package com.affinitas.chat.users.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ChatControoler is an MVC spring controller used for demo purpose and creates to UI for User 1 and User 2
 * To use it: open two browser tabs
 * on first tab use http://localhost:8080/user1
 * on second tab http://localhost:8080/user2
 * @author mohammad
 */
@Controller
public class ChatController {
	
	private String message = "Hello World";

	@GetMapping("/user1")
	public String connectUser1(Map<String, Object> model) {
		model.put("toUserId", "2");
		model.put("fromUserId", "1");
		return "user";
	}
	
	@GetMapping("/user2")
	public String connectUser2(Map<String, Object> model) {
		model.put("toUserId", "1");
		model.put("fromUserId", "2");
		return "user";
	}
}
