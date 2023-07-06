package com.example.ennead.controller;

import com.example.ennead.dto.ProfileRequestDto;
import com.example.ennead.entity.User;
import com.example.ennead.security.UserDetailsImpl;
import com.example.ennead.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    public ProfileRequestDto profileRequestDto;

    public AuthenticationManager authenticationManager;

    @GetMapping("/checkPwd")
    public boolean checkPassword(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @RequestParam String checkPassword
    ) {

        Long id = profileRequestDto.getId();

        return profileService.checkPassword(id, checkPassword);
    }

    @PutMapping("/")
    public boolean update(@RequestBody ProfileRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        if (profileService.checkNickname(requestDto, user.getId(), user.getNickname())) {
            return false;
        } else {
            profileService.update(requestDto, user);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())
            );


            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }

    }
}
