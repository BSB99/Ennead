package com.example.ennead.service;

import com.example.ennead.dto.SigninRequestDto;
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
        // 회원 중복 확인
        userRepository.findByNickname(nickname)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
                });

        userRepository.save(new User(requestDto, password));
    }

    public void signin(SigninRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록되지 않은 사용자입니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
