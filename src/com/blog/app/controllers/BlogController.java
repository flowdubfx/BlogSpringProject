package com.blog.app.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Post;
import com.blog.app.service.BlogService;
import com.blog.app.service.PostService;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
	public String showBlog(Model model, @PathVariable int blogId) {

		Blog blog = blogService.getBlog(blogId);

		Set<Post> posts = postService.findByBlog(blog);

		model.addAttribute("blog", blog);
		model.addAttribute("posts", posts);

		return "blog";
	}

}
