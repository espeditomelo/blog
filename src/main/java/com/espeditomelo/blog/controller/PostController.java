package com.espeditomelo.blog.controller;

import com.espeditomelo.blog.model.Category;
import com.espeditomelo.blog.model.Post;
import com.espeditomelo.blog.model.User;
import com.espeditomelo.blog.service.CategoryService;
import com.espeditomelo.blog.service.PostService;
import com.espeditomelo.blog.service.UserService;
import com.espeditomelo.blog.service.serviceImpl.ImageStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    ImageStorageService imageStorageService;

    private static final int PAGE_SIZE = 5;

    @GetMapping(value = "/")
    public String redirectToPosts(){
        return "redirect:/posts";
    }

//    @RequestMapping(value = "/posts", method = RequestMethod.GET)
//    public ModelAndView getPosts() {
//        ModelAndView modelAndView = new ModelAndView("posts");
//        List<Post> posts = postService.findAllWithCategoryAndUser();
//        modelAndView.addObject("posts", posts);
//        return modelAndView;
//    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts(@RequestParam(value = "page", defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("posts");

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Post> postsPage = postService.findAllWithCategoryAndUserPageable(pageable);

        modelAndView.addObject("posts", postsPage.getContent());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", postsPage.getTotalPages());
        modelAndView.addObject("totalItems", postsPage.getTotalElements());
        modelAndView.addObject("hasNext", postsPage.hasNext());
        modelAndView.addObject("hasPrev", postsPage.hasPrevious());

        return modelAndView;
    }


//    @RequestMapping(value = "/postsbycategory/{id}", method = RequestMethod.GET)
//    public ModelAndView getPostsByCategory(@PathVariable("id") long id) {
//        ModelAndView modelAndView = new ModelAndView("posts");
//        List<Post> posts = postService.findAllWithCategoryAndUserByCategory(id);
//        modelAndView.addObject("posts", posts);
//        return modelAndView;
//    }

    @RequestMapping(value = "/postsbycategory/{id}", method = RequestMethod.GET)
    public ModelAndView getPostsByCategory(@PathVariable("id") long id,
                                           @RequestParam(value = "page", defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("posts");

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        //List<Post> posts = postService.findAllWithCategoryAndUserByCategory(id);
        Page<Post> postsPage = postService.findAllWithCategoryAndUserByCategoryPageable(id, pageable);

        modelAndView.addObject("posts", postsPage.getContent());
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", postsPage.getTotalPages());
        modelAndView.addObject("totalItems", postsPage.getTotalElements());
        modelAndView.addObject("hasNext", postsPage.hasNext());
        modelAndView.addObject("hasPrev", postsPage.hasPrevious());
        modelAndView.addObject("categoryId", id);

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
                                 @RequestParam(value = "categoryIds", required = true) List<Long> categoryIds,
                                 @RequestParam(value = "mainImage", required = false) MultipartFile mainImage) {

        if(bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("postForm");
            modelAndView.addObject("categories", categoryService.findAll());
            modelAndView.addObject("users", userService.findAllEnabled());
            modelAndView.addObject("post", post);
            modelAndView.addObject("selectedCategoryIds", categoryIds != null ? categoryIds : new ArrayList<>());
            modelAndView.addObject("message", "All required fields must be completed");
            return modelAndView;
        }

        try {
            // image upload
            if(mainImage != null && !mainImage.isEmpty()) {
                String imageUrl = imageStorageService.store(mainImage);
                post.setMainImageUrl(imageUrl);
            }

            // categories
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

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating post: " + e.getMessage());
            return getErrorView(post, categoryIds);
        }



    }

    private ModelAndView getErrorView(@Valid Post post, List<Long> categoryIds) {
        ModelAndView modelAndView = new ModelAndView("postForm");
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("users", userService.findAllEnabled());
        modelAndView.addObject("post", post);
        modelAndView.addObject("selectedCategoryIds", categoryIds != null ? categoryIds : new ArrayList<>());
        modelAndView.addObject("message", "All required fields must be completed");
        return modelAndView;
    }
}
