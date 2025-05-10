package com.spoon.demo.controller;

import com.spoon.demo.model.BlogPost;
import com.spoon.demo.service.BlogPostService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.spoon.demo.model.enums.Category;

@RestController
@RequestMapping("/api/blog")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }

    @GetMapping("/category/{category}")
    public List<BlogPost> getPostsByCategory(@PathVariable Category category) {
        return blogPostService.getPostsByCategory(category);
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost blogPost) {
        return blogPostService.createPost(blogPost);
    }
}
