package com.espeditomelo.blog.service.serviceImpl;

import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.repository.PostRepository;
import com.espeditomelo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Post> findAllWithCategoryAndUserByCategory(Long id) {
        return postRepository.findAllWithCategoryAndUserByCategory(id);
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
