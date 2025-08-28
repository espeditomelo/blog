package com.espeditomelo.blog.controller;

import com.espeditomelo.blog.model.Category;
import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.User;
import com.espeditomelo.blog.service.CategoryService;
import com.espeditomelo.blog.service.PostService;
import com.espeditomelo.blog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView modelAndView = new ModelAndView("posts");
        List<Post> posts = postService.findAllWithCategoryAndUser();
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetailed(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("postDetailed");
        Post post = postService.findById(id);
        modelAndView.addObject("post", post);
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdminForm() {
        return "adminForm";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public ModelAndView getPostForm() {
        ModelAndView modelAndView = new ModelAndView("postForm");
        List<Category> categories = categoryService.findAll();
        List<User> users = userService.findAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("users", users);
        modelAndView.addObject("post", new Post());
        return modelAndView;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult  bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "redirect:/newpost";
        }
        postService.save(post);
        return "redirect:/posts";
    }

}
