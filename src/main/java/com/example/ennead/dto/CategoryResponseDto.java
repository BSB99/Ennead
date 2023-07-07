package com.example.ennead.dto;

import com.example.ennead.entity.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponseDto {
    private List<BoardResponseDto> categoryList;
    Long id;
    String  name;

    public CategoryResponseDto(List<BoardResponseDto> categoryList , Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.categoryList = categoryList;
    }
}

