package com.espeditomelo.blog.service;

import com.espeditomelo.blog.model.Post;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

public interface PostService {

    List<Post> findAll();
    Post findById(Long id);
    Post save(Post post);

}
