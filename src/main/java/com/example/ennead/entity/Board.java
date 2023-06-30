package com.example.ennead.entity;

import com.example.ennead.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Board")
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "nickname" , nullable = false , unique = true)
    private String nickname;

    @Column(name = "content" , nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Board(Category category , BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.category = category;
        this.nickname = boardRequestDto.getNickname();
    }

    public void update(BoardRequestDto requestDto) {
        this.title =  requestDto.getTitle();
        this.content =  requestDto.getContent();

    }
}
