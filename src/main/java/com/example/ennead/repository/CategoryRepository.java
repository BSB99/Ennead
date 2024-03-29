package com.example.ennead.repository;

import com.example.ennead.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByName(String name);

}
