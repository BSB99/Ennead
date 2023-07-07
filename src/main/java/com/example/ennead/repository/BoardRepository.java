package com.example.ennead.repository;

import com.example.ennead.entity.Board;
import com.example.ennead.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByCategory(Category category, Pageable pageable);

}
