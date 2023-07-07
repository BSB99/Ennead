package com.example.ennead.service;

import com.example.ennead.dto.ApiResponseDto;
import com.example.ennead.dto.CommentLikeRequestDto;
import com.example.ennead.dto.CommentRequestDto;
import com.example.ennead.entity.Board;
import com.example.ennead.entity.Comment;
import com.example.ennead.entity.CommentLike;
import com.example.ennead.entity.User;
import com.example.ennead.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentService commentService;
    private final CommentLikeRepository commentLikeRepository;
    public ApiResponseDto createCommentLike(Long commentNo, User user) {
        Comment comment = commentService.findComment(commentNo);
        CommentLike commentLike = new CommentLike(comment, user);
        Optional<CommentLike> findCommentLike= commentLikeRepository.findByUserAndComment(user, comment);

        if( findCommentLike.isEmpty()) {
            commentLikeRepository.save(commentLike);
        } else {
            throw new IllegalArgumentException("댓글 하나에 좋아요는 한번만 할 수 있습니다!");
        }

        return new ApiResponseDto("댓글 좋아요 완료", 200);

    }

    public ApiResponseDto deleteCommentLike(Long commentNo, User user) {
        Comment comment = commentService.findComment(commentNo);

        CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment).orElseThrow(() -> {
            throw new IllegalArgumentException("좋아요가 존재하지 않습니다");
        });

        if (commentLike.getUser().equals(user)) {
            throw new IllegalArgumentException("좋아요를 누른 유저가 아닙니다!");
        }

        commentLikeRepository.delete(commentLike);
        return new ApiResponseDto("댓글 좋아요 취소", 200);
    }
}
