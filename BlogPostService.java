package com.spoon.demo.service;

import com.spoon.demo.model.BlogPost;
import com.spoon.demo.model.enums.Category;
import com.spoon.demo.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public List<BlogPost> getPostsByCategory(Category category) {
        return blogPostRepository.findByCategory(category);
    }

    public BlogPost createPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }
}
