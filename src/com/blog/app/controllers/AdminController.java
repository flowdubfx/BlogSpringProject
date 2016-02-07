package com.blog.app.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Member;
import com.blog.app.entity.Post;
import com.blog.app.service.BlogService;
import com.blog.app.service.MemberService;
import com.blog.app.service.PostService;

@Controller
public class AdminController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	@Autowired
	private MemberService memberService;

	@RequestMapping("/admin")
	public String admin(Model model, Principal principal) {
		String username = principal.getName();

		List<Blog> blogs = blogService.getBlogs();
		List<Post> posts = postService.getPosts();
		List<Member> members = memberService.getMembers();

		model.addAttribute("username", username);
		model.addAttribute("blogs", blogs);
		model.addAttribute("posts", posts);
		model.addAttribute("members", members);

		return "admin";
	}

	@RequestMapping(value = "/admin/edit/blog/{blogId}", method = RequestMethod.GET)
	public String showEditBlog(@PathVariable("blogId") int blogId, Model model) {
		Blog blog = blogService.getBlog(blogId);
		model.addAttribute("blog", blog);

		return "blog_edit";
	}

	@RequestMapping(value = "/admin/edit/blog/{blogId}", method = RequestMethod.POST)
	public String editBlog(@PathVariable("blogId") int blogId, Blog newBlog) {

		Blog blog = blogService.getBlog(blogId);
		blog.setName(newBlog.getName());
		blogService.update(blog);

		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/delete/blog/{blogId}")
	public String deleteBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogService.getBlog(blogId);
		blogService.delete(blog);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/edit/post/{postId}", method = RequestMethod.GET)
	public String showEditPost(@PathVariable("postId") int postId, Model model) {

		Post post = postService.findOne(postId);
		model.addAttribute("post", post);

		return "post_edit";
	}

	@RequestMapping(value = "/admin/edit/post/{postId}", method = RequestMethod.POST)
	public String EditPost(@PathVariable("postId") int postId, Post newPost) {

		Post post = postService.findOne(postId);
		post.setName(newPost.getName());
		post.setText(newPost.getText());

		postService.update(post);

		return "redirect:/admin";
	}

	@RequestMapping(value = "/admin/delete/post/{postId}")
	public String deletePost(@PathVariable("postId") int postId) {
		Post post = postService.findOne(postId);
		postService.delete(post);
		return "redirect:/admin";
	}

}
