package com.example.ennead.entity;

import com.example.ennead.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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


    @Column(name = "content" , nullable = false)
    private String content;
    @Column(name = "count" , nullable = false)
    private Long boardCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Board(Category category , BoardRequestDto boardRequestDto, User user){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.category = category;
        this.boardCount = 0L;
        this.user = user;
    }

    public void update(BoardRequestDto requestDto) {
        this.title =  requestDto.getTitle();
        this.content =  requestDto.getContent();

    }
}
