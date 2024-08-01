package org.example.websocketchat2.controller;

import lombok.RequiredArgsConstructor;
import org.example.websocketchat2.entity.ChatListEntity;
import org.example.websocketchat2.entity.ChatroomEntity;
import org.example.websocketchat2.service.ChatService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chat")
    public String chatList(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<ChatListEntity> chatList = chatService.getChatList(userDetails.getUsername());
        model.addAttribute("chatList", chatList);
        return "chatlist";
    }

    @GetMapping("/chat/{id}")
    public String chatRoom(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ChatroomEntity chatroom = chatService.getChatroom(id);
        if (!chatService.hasAccess(userDetails.getUsername(), chatroom)) {
            return "redirect:/chat";
        }
        model.addAttribute("chatroom", chatroom);
        return "chatroom";
    }

    @PostMapping("/chat/new")
    public String newChat(@RequestParam String username, @AuthenticationPrincipal UserDetails userDetails) {
        ChatroomEntity chatroom = chatService.createChatroom(userDetails.getUsername(), username);
        return "redirect:/chat/" + chatroom.getId();
    }
}