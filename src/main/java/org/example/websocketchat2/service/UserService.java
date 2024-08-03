package org.example.websocketchat2.service;

import lombok.RequiredArgsConstructor;
import org.example.websocketchat2.entity.UserEntity;
import org.example.websocketchat2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity registerNewUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}