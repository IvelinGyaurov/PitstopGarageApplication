package com.pitstop.garage.user.service;

import com.pitstop.garage.exceptions.UserAlreadyExistException;
import com.pitstop.garage.user.model.User;
import com.pitstop.garage.user.model.UserRole;
import com.pitstop.garage.user.repository.UserRepository;
import com.pitstop.garage.web.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.pitstop.garage.exceptions.UserAlreadyExistExceptionMessage.EMAIL_ALREADY_EXIST;
import static com.pitstop.garage.exceptions.UserAlreadyExistExceptionMessage.USERNAME_ALREADY_EXIST;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest registerRequest) {

        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            log.error("Username {} already exist", registerRequest.getUsername());
            throw new UserAlreadyExistException(USERNAME_ALREADY_EXIST);
        }

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            log.error("Email {} already exist", registerRequest.getEmail());
            throw new UserAlreadyExistException(EMAIL_ALREADY_EXIST);
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(UserRole.USER)
                .enabled(true)
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();

        int usersCount = userRepository.findAll().size();
        if (usersCount == 0) {
            user.setRole(UserRole.ADMIN);
        }

        userRepository.save(user);
        log.info("Registered user {}", user.getUsername());
    }
}
