package org.example.websocketchat2.controller;

import lombok.RequiredArgsConstructor;
import org.example.websocketchat2.dto.ChatMessage;
import org.example.websocketchat2.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        chatService.saveMessage(chatMessage);
        messagingTemplate.convertAndSend("/topic/messages/" + chatMessage.getChatroomId(), chatMessage);
    }
}