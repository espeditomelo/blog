package com.espeditomelo.blog.model.repository;

import com.espeditomelo.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT DISTINCT p FROM Post p " +
            "LEFT JOIN FETCH p.user " +
            "LEFT JOIN FETCH p.postCategories pc " +
            "LEFT JOIN FETCH pc.category " +
            "WHERE p.status = 'A' " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllWithCategoryAndUser();

    @Query("SELECT DISTINCT p FROM Post p " +
            "LEFT JOIN FETCH p.user " +
            "LEFT JOIN FETCH p.postCategories pc " +
            "LEFT JOIN FETCH pc.category " +
            "WHERE p.status = 'A' " +
            "ORDER BY p.createdAt DESC")
    Page<Post> findAllWithCategoryAndUserPageable(Pageable pageable);

    @Query("SELECT DISTINCT p FROM Post p " +
            "LEFT JOIN FETCH p.user " +
            "LEFT JOIN FETCH p.postCategories pc " +
            "LEFT JOIN FETCH pc.category " +
            "WHERE p.status = 'A' " +
            "AND pc.category.id = :id " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllWithCategoryAndUserByCategory(@Param("id") Long id);

    @Query("SELECT DISTINCT p FROM Post p " +
            "LEFT JOIN FETCH p.user " +
            "LEFT JOIN FETCH p.postCategories pc " +
            "LEFT JOIN FETCH pc.category " +
            "WHERE p.status = 'A' " +
            "AND pc.category.id = :id " +
            "ORDER BY p.createdAt DESC")
    Page<Post> findAllWithCategoryAndUserByCategoryPageable(@Param("id") Long id, Pageable pageable);
}
