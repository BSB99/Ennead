package com.example.ennead.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class PageCategoryBoardDto {
    List<BoardResponseDto> boardList;
    PageInfo pageInfo;
    List<CategoryContentsResponseDto> categoryDtoList;
    public PageCategoryBoardDto(List<BoardResponseDto> boardList, PageInfo pageInfo , List<CategoryContentsResponseDto> categoryDtoList) {
        this.boardList = boardList;
        this.pageInfo = pageInfo;
        this.categoryDtoList = categoryDtoList;
    }
}
