package com.example.ennead.repository;

import com.example.ennead.entity.Comment;
import com.example.ennead.entity.CommentLike;
import com.example.ennead.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
