package com.spring.chatting_room.controller;

import com.spring.chatting_room.model.ChatMessage;


import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {
	
    @MessageMapping("/chat.sendMessage/{department}")
    @SendTo("/topic/public/{department}")
    public ChatMessage sendMessage(@DestinationVariable("department") String department, @Payload ChatMessage chatMessage) {
    	System.out.println("ChatController::sendMessage() : "+chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
    	System.out.println("ChatController::addUser() : "+chatMessage);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        
        return chatMessage;
    }
    
    
    @MessageMapping("/chat/{receiver}")
	@SendTo("/topic/messages/{receiver}")
    public ChatMessage send(@DestinationVariable("receiver") String receiver,
    		@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) throws Exception
    {
		System.out.println("send() : "+receiver+" , type : "+chatMessage.getType()); 
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
	    return chatMessage;
    }

}
