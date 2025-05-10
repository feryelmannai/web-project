package com.spoon.demo.repository;

import com.spoon.demo.model.BlogPost;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.spoon.demo.model.enums.Category;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findByCategory(Category category);
}
