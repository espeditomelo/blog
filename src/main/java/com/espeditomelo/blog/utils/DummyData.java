package com.espeditomelo.blog.utils;

import com.espeditomelo.blog.model.Category;
import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.User;
import com.espeditomelo.blog.model.repository.CategoryRepository;
import com.espeditomelo.blog.model.repository.PostRepository;
import com.espeditomelo.blog.model.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DummyData {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;


    @PostConstruct
    public void savePosts(){

        Optional<User> userOptional = userRepository.findById(1L);
        Optional<Category> categoryOptional = categoryRepository.findById(1L);

        if(userOptional.isPresent() && categoryOptional.isPresent()){
            User user = userOptional.get();
            Category category = categoryOptional.get();

            Post post = new Post();
            post.setTitle("tittle");
            post.setBody("body");
            post.setStatus("a");
            post.setUser(user);
            post.setCategory(category);
            System.out.println(postRepository.save(post));
        } else {
            System.out.println("User or Category not found with IDs");
        }


    }

}
