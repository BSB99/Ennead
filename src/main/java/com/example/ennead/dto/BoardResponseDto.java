package com.example.ennead.dto;

import com.example.ennead.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String category;
//    private List<CommentResponseDto> commentList;
    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.title =board.getTitle();
        this.content =board.getContent();
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getUpdatedAt();
        this.nickname = board.getNickname();
        this.category = board.getCategory().getName();
    }
//    public BoardResponseDto (Board board , List<CommentResponseDto> commentList){
//        this.id = board.getId();
//        this.title =board.getTitle();
//        this.content =board.getContent();
//        this.createAt = board.getCreatedAt();
//        this.modifiedAt = board.getModifiedAt();
//        this.commentList = commentList;
//    }
}
