package com.example.ennead.controller;

import com.example.ennead.dto.ProfileRequestDto;
import com.example.ennead.entity.User;
import com.example.ennead.security.UserDetailsImpl;
import com.example.ennead.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    private ProfileRequestDto profileRequestDto;

    @GetMapping("/checkPwd")
    public boolean checkPassword(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                 @RequestParam String checkPassword
    ) {

        Long id = profileRequestDto.getId();

        return profileService.checkPassword(id, checkPassword);
    }

    @PutMapping("/{user_no}}")
    public boolean update(@RequestBody ProfileRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        if (profileService.checkNickname(requestDto,user.getId(), user.getNickname()) {
            return false;
        } else {

            profileService.update(requestDto,user);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );


            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        }
    }
}
