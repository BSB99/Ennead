package com.example.ennead.controller;

import com.example.ennead.dto.*;
import com.example.ennead.jwt.JwtUtil;
import com.example.ennead.security.UserDetailsImpl;
import com.example.ennead.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
        userService.signin(requestDto, response);
        return new ApiResponseDto("로그인 완료", HttpStatus.OK.value());
    }

    // 회원탈퇴
    @PostMapping("/delete")
    public ApiResponseDto deleteUser(@RequestBody DeleteRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        userService.deleteUser(requestDto, userDetails.getUser());
        SecurityContextHolder.clearContext();
        return new ApiResponseDto("회원 탈퇴 완료", HttpStatus.OK.value());
    }

    // 로그아웃
    @PostMapping("/signout")
    public ApiResponseDto signout(HttpServletResponse response) {
        jwtUtil.expireCookie(response);
        return new ApiResponseDto("로그아웃 완료", HttpStatus.OK.value());
    }

    @PutMapping("/update")
    public ApiResponseDto updateProfile(@Valid @RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.updateProfile(requestDto, userDetails.getUser());
    }
}
