package com.espeditomelo.blog.model.repository;

import com.espeditomelo.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
