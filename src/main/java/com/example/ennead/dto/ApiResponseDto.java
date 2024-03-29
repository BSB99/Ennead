package com.example.ennead.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDto {
    private String msg;
    private int statusCode;

    public ApiResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
