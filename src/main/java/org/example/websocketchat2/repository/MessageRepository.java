package org.example.websocketchat2.repository;

import org.example.websocketchat2.entity.ChatroomEntity;
import org.example.websocketchat2.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByChatroomOrderByIdAsc(ChatroomEntity chatroom);
}