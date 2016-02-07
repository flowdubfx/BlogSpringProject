package com.blog.app.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Set<Post> findByBlog(Blog blog);

	Post findByName(String name);

}
