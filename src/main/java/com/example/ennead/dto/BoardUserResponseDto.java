package com.example.ennead.dto;

import com.example.ennead.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BoardUserResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String category;
    private Long BoardCount;
    public BoardUserResponseDto(Board board){
        this.id = board.getId();
        this.title =board.getTitle();
        this.content =board.getContent();
        this.createAt = board.getCreatedAt();
        this.modifiedAt = board.getUpdatedAt();
        this.category = board.getCategory().getName();
        this.BoardCount = board.getBoardCount();
    }
}
