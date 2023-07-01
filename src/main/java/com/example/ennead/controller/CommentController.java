package com.example.ennead.controller;

import com.example.ennead.dto.ApiResponseDto;
import com.example.ennead.dto.CommentRequestDto;
import com.example.ennead.dto.CommentResponseDto;
import com.example.ennead.jwt.JwtUtil;
import com.example.ennead.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{post_no}/comment")
    public CommentResponseDto createComment(@RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String data, @PathVariable Long post_no, @RequestBody CommentRequestDto request) {
        return commentService.createComment(data, post_no, request);
    }

    @PutMapping("/{post_no}/comment/{comment_no}")
    public CommentResponseDto updateComment(@RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String data, @PathVariable Long post_no, @PathVariable Long comment_no, @RequestBody CommentRequestDto request) {
        return commentService.updateComment(data, post_no, comment_no, request);
    }

    @DeleteMapping("/{post_no}/comment/{comment_no}")
    public ApiResponseDto deleteComment(@RequestHeader(JwtUtil.AUTHORIZATION_HEADER) String data, @PathVariable Long post_no, @PathVariable Long comment_no) {
        return commentService.deleteComment(data, post_no, comment_no);
    }
}
