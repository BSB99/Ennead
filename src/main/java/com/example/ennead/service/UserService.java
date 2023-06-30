package com.example.ennead.service;

import com.example.ennead.dto.SignupRequestDto;
import com.example.ennead.entity.User;
import com.example.ennead.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void signup(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        userRepository.findByUsername(username)
            .ifPresent(user -> {
                throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            });

        userRepository.save(new User(username, nickname, password));
    }

}
