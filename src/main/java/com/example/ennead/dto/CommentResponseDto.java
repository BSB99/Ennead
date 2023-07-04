package com.example.ennead.dto;

import com.example.ennead.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String nickname;
    private int like_count;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.nickname = comment.getUser().getNickname();
        this.like_count = comment.getCommentList().size();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getUpdatedAt();
    }
}
