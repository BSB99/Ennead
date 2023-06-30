package com.example.ennead.controller;

import com.example.ennead.dto.CommentRequestDto;
import com.example.ennead.dto.CommentResponseDto;
import com.example.ennead.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{post_no}/comment")
    public CommentResponseDto createComment(@PathVariable Long post_no, @RequestBody CommentRequestDto request) {
        return commentService.createComment(post_no, request);
    }

    @PutMapping("/{post_no}/comment/{comment_no}")
    public CommentResponseDto updateComment(@PathVariable Long post_no, @PathVariable Long comment_no, @RequestBody CommentRequestDto request) {
        return commentService.updateComment(post_no, comment_no, request);
    }

}
