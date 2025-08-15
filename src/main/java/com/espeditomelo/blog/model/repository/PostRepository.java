package com.espeditomelo.blog.model.repository;

import com.espeditomelo.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
