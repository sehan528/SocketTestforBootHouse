package org.example.websocketchat2.repository;

import org.example.websocketchat2.entity.ChatroomEntity;
import org.example.websocketchat2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatroomRepository extends JpaRepository<ChatroomEntity, Long> {
    Optional<ChatroomEntity> findByUsersContainingAndUsersContaining(UserEntity user1, UserEntity user2);
}