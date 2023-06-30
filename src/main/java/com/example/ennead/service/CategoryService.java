package com.example.ennead.service;

import com.example.ennead.dto.CategoryContentsResponseDto;
import com.example.ennead.dto.CategoryRequestDto;
import com.example.ennead.entity.Category;
import com.example.ennead.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryContentsResponseDto registerCategory(CategoryRequestDto requestDto) {
        Category category = new Category(requestDto);
        return new CategoryContentsResponseDto(categoryRepository.save(category));
    }

    public List<CategoryContentsResponseDto> getCategorys() {
        List<CategoryContentsResponseDto> categoryContentsResponseDtoList =  new ArrayList<>();
        List<Category> categoryList =  categoryRepository.findAll();
        for (Category category : categoryList) {
            CategoryContentsResponseDto categoryContentsResponseDto = new CategoryContentsResponseDto(category);
            categoryContentsResponseDtoList.add(categoryContentsResponseDto);
        }
        return categoryContentsResponseDtoList;
    }
}
