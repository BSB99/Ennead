package com.example.ennead.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Getter
public class ProfileRequestDto {

    @NotBlank
    private String password;

    @URL
    private String imageUrl;

    private String nickname;
}