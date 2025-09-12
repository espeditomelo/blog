package com.espeditomelo.blog.service;

import com.espeditomelo.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

public interface PostService {

    List<Post> findAll();
    List<Post> findAllWithCategoryAndUser();
    Page<Post> findAllWithCategoryAndUserPageable(Pageable pageable);
    List<Post> findAllWithCategoryAndUserByCategory(Long id);
    Page<Post> findAllWithCategoryAndUserByCategoryPageable(Long id, Pageable pageable);
    Post findById(Long id);
    Post save(Post post);

}
