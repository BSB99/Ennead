package com.example.ennead.entity;

import com.example.ennead.dto.CategoryRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "Category")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , nullable = false , unique = true)
    String name;

    @OneToMany(mappedBy = "category" ,fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    public Category(CategoryRequestDto categoryRequestDto){
        this.name = categoryRequestDto.getName();
    }
}
