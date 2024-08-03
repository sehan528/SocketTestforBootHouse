package org.example.websocketchat2.repository;

import org.example.websocketchat2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByName(String name);
    boolean existsByName(String name);
}