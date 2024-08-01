package org.example.websocketchat2.service;

import lombok.RequiredArgsConstructor;
import org.example.websocketchat2.entity.ChatListEntity;
import org.example.websocketchat2.entity.ChatroomEntity;
import org.example.websocketchat2.entity.MessageEntity;
import org.example.websocketchat2.entity.UserEntity;
import org.example.websocketchat2.repository.ChatListRepository;
import org.example.websocketchat2.repository.ChatroomRepository;
import org.example.websocketchat2.repository.MessageRepository;
import org.example.websocketchat2.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ChatService {
    private final ChatListRepository chatListRepository;
    private final ChatroomRepository chatroomRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public List<ChatListEntity> getChatList(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return chatListRepository.findByUser(user);
    }

    public ChatroomEntity getChatroom(Long id) {
        return chatroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chatroom not found"));
    }

    public boolean hasAccess(String email, ChatroomEntity chatroom) {
        UserEntity user = userRepository.findByEmail(email);
        return chatListRepository.existsByUserAndChatroom(user, chatroom);
    }

    @Transactional
    public ChatroomEntity createChatroom(String email1, String email2) {
        UserEntity user1 = userRepository.findByEmail(email1);
        UserEntity user2 = userRepository.findByEmail(email2);

        ChatroomEntity chatroom = ChatroomEntity.builder()
                .name(user1.getName() + ", " + user2.getName())
                .build();
        chatroomRepository.save(chatroom);

        ChatListEntity chatList1 = ChatListEntity.builder()
                .user(user1)
                .chatroom(chatroom)
                .build();
        ChatListEntity chatList2 = ChatListEntity.builder()
                .user(user2)
                .chatroom(chatroom)
                .build();
        chatListRepository.saveAll(Arrays.asList(chatList1, chatList2));

        return chatroom;
    }

    @Transactional
    public MessageEntity saveMessage(Long chatroomId, String senderEmail, String content) {
        ChatroomEntity chatroom = getChatroom(chatroomId);
        UserEntity sender = userRepository.findByEmail(senderEmail);

        MessageEntity message = MessageEntity.builder()
                .chatroom(chatroom)
                .sender(sender)
                .message(content)
                .build();

        return messageRepository.save(message);
    }

    public List<MessageEntity> getChatroomMessages(Long chatroomId) {
        ChatroomEntity chatroom = getChatroom(chatroomId);
        return messageRepository.findByChatroomOrderByIdAsc(chatroom);
    }

    public List<ChatListEntity> getRecentChats(String name) {
        UserEntity user = userRepository.findByName(name);
        return chatListRepository.findByUserOrderByIdDesc(user);
    }

    @Transactional
    public Long getOrCreateChatroom(String name1, String name2) {
        UserEntity user1 = userRepository.findByName(name1);
        UserEntity user2 = userRepository.findByName(name2);

        return chatroomRepository.findByUsersContainingAndUsersContaining(user1, user2)
                .map(ChatroomEntity::getId)
                .orElseGet(() -> {
                    ChatroomEntity newChatroom = ChatroomEntity.builder()
                            .name(name1 + ", " + name2)
                            .build();
                    newChatroom.getUsers().add(user1);
                    newChatroom.getUsers().add(user2);
                    chatroomRepository.save(newChatroom);

                    ChatListEntity chatList1 = ChatListEntity.builder()
                            .user(user1)
                            .chatroom(newChatroom)
                            .build();
                    ChatListEntity chatList2 = ChatListEntity.builder()
                            .user(user2)
                            .chatroom(newChatroom)
                            .build();
                    chatListRepository.saveAll(Arrays.asList(chatList1, chatList2));

                    return newChatroom.getId();
                });
    }

}