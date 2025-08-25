package com.espeditomelo.blog.controller;

import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView modelAndView = new ModelAndView("posts");
        //List<Post> posts = postService.findAll();
        List<Post> posts = postService.findAllWithCategory();
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

}
