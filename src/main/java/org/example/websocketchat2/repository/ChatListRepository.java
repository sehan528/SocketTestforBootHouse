package org.example.websocketchat2.repository;

import org.example.websocketchat2.entity.ChatListEntity;
import org.example.websocketchat2.entity.ChatroomEntity;
import org.example.websocketchat2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatListRepository extends JpaRepository<ChatListEntity, Long> {
    List<ChatListEntity> findByUser(UserEntity user);
    boolean existsByUserAndChatroom(UserEntity user, ChatroomEntity chatroom);
//    List<ChatListEntity> findByUserOrderByUpdatedAtDesc(UserEntity user);
    List<ChatListEntity> findByUserOrderByIdDesc(UserEntity user);
}