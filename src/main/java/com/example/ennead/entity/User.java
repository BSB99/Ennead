package com.example.ennead.entity;

import com.example.ennead.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<Board> boardList = new ArrayList<>();

    public User(SignupRequestDto requestDto, String password) {
        this.username = requestDto.getUsername();
        this.nickname = requestDto.getNickname();
        this.password = password;
        this.imageUrl = requestDto.getImageUrl();
    }
}
