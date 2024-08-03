package org.example.websocketchat2.controller;

import lombok.RequiredArgsConstructor;
import org.example.websocketchat2.entity.ChatListEntity;
import org.example.websocketchat2.entity.UserEntity;
import org.example.websocketchat2.service.ChatService;
import org.example.websocketchat2.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

    private final ChatService chatService;
    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<ChatListEntity> recentChats = chatService.getRecentChats(userDetails.getUsername());
        model.addAttribute("recentChats", recentChats);
        model.addAttribute("username", userDetails.getUsername());
        return "welcome";
    }

    @PostMapping("/startChat")
    public String startChat(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam String targetName,
                            Model model) {
        return userService.findByName(targetName)
                .map(targetUser -> {
                    Long chatroomId = chatService.getOrCreateChatroom(userDetails.getUsername(), targetName);
                    return "redirect:/chat/" + chatroomId;
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "유효하지 않은 아이디입니다.");
                    return "welcome";
                });
    }
}