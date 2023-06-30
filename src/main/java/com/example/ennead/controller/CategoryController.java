package com.example.ennead.controller;

import com.example.ennead.dto.CategoryContentsResponseDto;
import com.example.ennead.dto.CategoryRequestDto;
import com.example.ennead.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category") // 카테고리 등록
    public CategoryContentsResponseDto registerCategory(@RequestBody CategoryRequestDto RequestDto){
        return categoryService.registerCategory(RequestDto);
    }
    @GetMapping("/category") //존재하는 카테고리 이름 get
    public List<CategoryContentsResponseDto> getCategorys(){
        return categoryService.getCategorys();
    }
}
