package com.example.ennead.service;

import com.example.ennead.dto.CommentRequestDto;
import com.example.ennead.dto.CommentResponseDto;
import com.example.ennead.entity.Comment;
import com.example.ennead.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public CommentResponseDto createComment(Long postNo, CommentRequestDto request) {
        Comment comment = new Comment(request);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
