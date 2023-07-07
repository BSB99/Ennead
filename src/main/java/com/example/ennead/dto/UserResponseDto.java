package com.example.ennead.dto;

import com.example.ennead.entity.Board;
import com.example.ennead.entity.Comment;
import com.example.ennead.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String nickname;
    private List<BoardUserResponseDto> boardList = new ArrayList<>();
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        for (Board board : user.getBoardList()) {
            boardList.add(new BoardUserResponseDto(board));
        }
    }
}
