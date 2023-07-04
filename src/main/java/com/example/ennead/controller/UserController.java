package com.example.ennead.controller;

import com.example.ennead.dto.ApiResponseDto;
import com.example.ennead.dto.SigninRequestDto;
import com.example.ennead.dto.SignupRequestDto;
import com.example.ennead.jwt.JwtUtil;
import com.example.ennead.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return new ApiResponseDto("회원 가입 완료", HttpStatus.OK.value());
    }

    // 로그인
    @PostMapping("/signin")
    public ApiResponseDto signin(@RequestBody SigninRequestDto requestDto, HttpServletResponse response) {
        userService.signin(requestDto);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(requestDto.getUsername()));
        return new ApiResponseDto("로그인 완료", HttpStatus.OK.value());
    }


}
