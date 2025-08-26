package com.espeditomelo.blog.model.repository;

import com.espeditomelo.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN FETCH p.category JOIN FETCH p.user")
    List<Post> findAllWithCategoryAndUser();
}
