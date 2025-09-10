package com.espeditomelo.blog.controller;

import com.espeditomelo.blog.model.Category;
import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.User;
import com.espeditomelo.blog.service.CategoryService;
import com.espeditomelo.blog.service.PostService;
import com.espeditomelo.blog.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories(){
        List<Category> categories = categoryService.findAllByNameAsc();
//        System.out.println("Carregando categorias: " + categories.size());
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for(Category c : categories){
            System.out.println(c.getName());
        }
        return categories;
    }

    @GetMapping(value = "/")
    public String redirectToPosts(){
        return "redirect:/posts";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts() {
        ModelAndView modelAndView = new ModelAndView("posts");
        List<Post> posts = postService.findAllWithCategoryAndUser();
//        System.out.println("Carregando posts: " + posts.size());
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping(value = "/postsbycategory/{id}", method = RequestMethod.GET)
    public ModelAndView getPostsByCategory(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("posts");
        List<Post> posts = postService.findAllWithCategoryAndUserByCategory(id);
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

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public ModelAndView getPostForm() {
        ModelAndView modelAndView = new ModelAndView("postForm");
        List<User> users = userService.findAllEnabled();
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("users", userService.findAllEnabled());
        modelAndView.addObject("post", new Post());
        modelAndView.addObject("selectedCategoryIds", new ArrayList<Long>());

        return modelAndView;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public ModelAndView savePost(@Valid Post post, BindingResult  bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam(value = "categoryIds", required = true) List<Long> categoryIds) {
        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("postForm");
            modelAndView.addObject("categories", categoryService.findAll());
            modelAndView.addObject("users", userService.findAllEnabled());
            modelAndView.addObject("post", post);
            modelAndView.addObject("selectedCategoryIds", categoryIds != null ? categoryIds : new ArrayList<>());
            modelAndView.addObject("message", "All required fields must be completed");
            return modelAndView;
        }

        if(categoryIds != null && !categoryIds.isEmpty()) {
            for(Long categoryId : categoryIds) {
                Category category = categoryService.findById(categoryId);
                if (category != null) {
                    post.addCategory(category);
                }
            }
        }
        postService.save(post);
        redirectAttributes.addFlashAttribute("success", "Post created successfully");
        return new ModelAndView("redirect:/posts");
    }
}
