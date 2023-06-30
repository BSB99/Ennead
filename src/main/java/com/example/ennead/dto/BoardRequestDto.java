package com.example.ennead.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String content;
    private String nickname; // user 랑 합치면 필요없어지는 변수
}
