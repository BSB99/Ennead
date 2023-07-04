package com.example.ennead.controller;

import com.example.ennead.dto.ApiResponseDto;
import com.example.ennead.dto.CommentRequestDto;
import com.example.ennead.dto.CommentResponseDto;
import com.example.ennead.jwt.JwtUtil;
import com.example.ennead.security.UserDetailsImpl;
import com.example.ennead.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{board_no}/comment")
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long board_no, @RequestBody CommentRequestDto request) {
        return commentService.createComment(userDetails.getUser(), board_no, request);
    }

    @PutMapping("/{board_no}/comment/{comment_no}")
    public CommentResponseDto updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long board_no, @PathVariable Long comment_no, @RequestBody CommentRequestDto request) {
        return commentService.updateComment(userDetails.getUser(), board_no, comment_no, request);
    }

    @DeleteMapping("/{board_no}/comment/{comment_no}")
    public ApiResponseDto deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long board_no, @PathVariable Long comment_no) {
        return commentService.deleteComment(userDetails.getUser(), board_no, comment_no);
    }

    // 댓글 좋아요 테스트 기능.
    @GetMapping("/comment/{comment_no}")
    public CommentResponseDto getComments(@PathVariable Long comment_no) {
        return commentService.getComment(comment_no);
    }
}
