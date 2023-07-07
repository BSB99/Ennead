package com.example.ennead.controller;

import com.example.ennead.dto.ApiResponseDto;
import com.example.ennead.dto.CommentLikeRequestDto;
import com.example.ennead.security.UserDetailsImpl;
import com.example.ennead.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("comment/{comment_no}")
    public ApiResponseDto createCommentLike(@PathVariable Long comment_no, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentLikeService.createCommentLike(comment_no, userDetails.getUser());
    }

    @DeleteMapping("comment/{comment_no}")
    public ApiResponseDto deleteCommentLike(@PathVariable Long comment_no, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentLikeService.deleteCommentLike(comment_no, userDetails.getUser());
    }
}
