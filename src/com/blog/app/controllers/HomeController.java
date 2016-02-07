package com.blog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Post;
import com.blog.app.service.BlogService;
import com.blog.app.service.PostService;

@Controller
public class HomeController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		List<Post> posts = postService.getPosts();
		List<Blog> blogs = blogService.getBlogs();

		model.addAttribute("blogs", blogs);
		model.addAttribute("posts", posts);
		return "home";
	}

}
