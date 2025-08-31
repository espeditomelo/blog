package com.espeditomelo.blog.utils;

import com.espeditomelo.blog.model.Category;
import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.User;
import com.espeditomelo.blog.model.repository.CategoryRepository;
import com.espeditomelo.blog.model.repository.PostRepository;
import com.espeditomelo.blog.model.repository.UserRepository;
import com.espeditomelo.blog.service.serviceImpl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DummyData {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserServiceImpl userService;

    @PostConstruct
    public void savePosts(){

//        User user1 =  new User();
//        user1.setUsername("antonio");
//        user1.setAdmin(false);
//        user1.setPassword("123");
//        user1.setEmail("e@gmail.com");
//        userRepository.save(user1);
//
//        Category category1 = new Category();
//        category1.setName("database");
//        categoryRepository.save(category1);

        User user2 =  new User();
        user2.setUsername("admin");
        user2.setAdmin(true);
        user2.setPassword("123");
        user2.setEmail("admin@gmail.com");
        // userRepository.save(user2);
        userService.save(user2);

//        Category category2 = new Category();
//        category2.setName("database");
//        categoryRepository.save(category2);

        /*Optional<User> userOptional = userRepository.findById(1L);
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
        } */

//        List<Post> posts = new ArrayList<>();
//
//        Post post1 = new Post();
//
//        Optional<User> userOptional = userRepository.findById(1L);
//        Optional<Category> categoryOptional = categoryRepository.findById(1L);
//
//        Optional<User> userOptional2 = userRepository.findById(2L);
//        Optional<Category> categoryOptional2 = categoryRepository.findById(2L);
//
//        //if(userOptional.isPresent() && categoryOptional.isPresent()){
//            User user = userOptional.get();
//            Category category = categoryOptional.get();
//
//            Post post = new Post();
//            post.setTitle("Initiate java");
//            post.setBody("ldkjfdjfjsdfskdfkjsdkjfksdkfjkd");
//            post.setStatus("a");
//            post.setUser(user);
//            post.setCategory(category);
//
//        posts.add(post);
//
//        Post post2 = new Post();
//        post2.setTitle("Initiate oracle");
//        post2.setBody("ldkjfdjfjsdfskdfalsdkdsldkaskdkdksdkkjsdkjfksdkfjkd");
//        post2.setStatus("a");
//        post2.setUser(user2);
//        post2.setCategory(category2);
//
//        posts.add(post2);

           // System.out.println(postRepository.save(post));
//        } else {
//            System.out.println("User or Category not found with IDs");
//        }

//        for(Post p : posts) {
//            postRepository.save(p);
//        }


    }

}
