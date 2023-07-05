package com.example.ennead.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class PageCategoryBoardDto {
    List<BoardResponseDto> boardList;
    PageInfo pageInfo;
    public PageCategoryBoardDto(List<BoardResponseDto> boardList, PageInfo pageInfo) {
        this.boardList = boardList;
        this.pageInfo = pageInfo;
    }
}
