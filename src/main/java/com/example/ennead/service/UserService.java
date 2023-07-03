package com.example.ennead.service;

import com.example.ennead.dto.SignupRequestDto;
import com.example.ennead.entity.User;
import com.example.ennead.jwt.JwtUtil;
import com.example.ennead.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
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

    public User getUser(String token) {
        Claims claims = jwtUtil.getUserInfoFromToken(jwtUtil.substringToken(token));

        return userRepository.findByUsername((String)claims.get("sub")).orElseThrow(() ->
                new NullPointerException("유저가 존재하지 않습니다"));
    }

}
