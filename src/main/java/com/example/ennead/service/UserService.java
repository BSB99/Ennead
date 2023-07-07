package com.example.ennead.service;

import com.example.ennead.dto.*;
import com.example.ennead.entity.User;
import com.example.ennead.jwt.JwtUtil;
import com.example.ennead.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public void signin(SigninRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록되지 않은 사용자입니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, response);
    }

    public void deleteUser(DeleteRequestDto requestDto, User user) {

        String password = requestDto.getPassword();

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);

    }

    @Transactional
    public ApiResponseDto updateProfile(ProfileRequestDto requestDto, User user) {

        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String imageUrl = requestDto.getImageUrl();

        User admin = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> {
            throw new IllegalArgumentException("user가 존재하지 않습니다");
        });

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(nickname != null) {
            userRepository.findByNickname(nickname)
                    .ifPresent(u -> {
                        throw new IllegalArgumentException("이미 사용중인 닉네임입니다.");
                    });
            admin.setNickname(nickname);
        }

        if(imageUrl != null) {
            admin.setImageUrl(imageUrl);
        }

        return new ApiResponseDto("프로필 변경 성공", HttpStatus.OK.value());
    }

    public UserResponseDto userBoard(User auth) {
        User user = userRepository.findByUsername(auth.getUsername()).orElseThrow(() -> {
            throw new IllegalArgumentException("user가 존재하지 않습니다");
        });

        return new UserResponseDto(user);
    }
}
