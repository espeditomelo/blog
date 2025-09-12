package com.espeditomelo.blog.service.serviceImpl;

import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.repository.PostRepository;
import com.espeditomelo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAllWithCategoryAndUser() {
        return postRepository.findAllWithCategoryAndUser();
    }

    @Override
    public Page<Post> findAllWithCategoryAndUserPageable(Pageable pageable) {
        return postRepository.findAllWithCategoryAndUserPageable(pageable);
    }

    @Override
    public List<Post> findAllWithCategoryAndUserByCategory(Long id) {
        return postRepository.findAllWithCategoryAndUserByCategory(id);
    }

    @Override
    public Page<Post> findAllWithCategoryAndUserByCategoryPageable(Long id, Pageable pageable) {
        return postRepository.findAllWithCategoryAndUserByCategoryPageable(id, pageable);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
