package com.blog.app.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Member;
import com.blog.app.entity.Post;
import com.blog.app.service.BlogService;
import com.blog.app.service.MemberService;
import com.blog.app.service.PostService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private PostService postService;

	@ModelAttribute("blog")
	public Blog constructBlog() {
		return new Blog();
	}

	@ModelAttribute("post")
	public Post constructPost() {
		return new Post();
	}

	@RequestMapping(value = "/member")
	public String showMemberPage(Model model, Principal principal) {

		String username = principal.getName();
		Member member = memberService.findOneWithBlogs(username);

		List<Blog> blogs = blogService.findByMember(member);
		model.addAttribute("blogs", blogs);

		model.addAttribute("member", member);
		return "member";
	}

	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public String addBlog(Model model, @Valid @ModelAttribute("blog") Blog blog, BindingResult result,
			Principal principal) {

		if (result.hasErrors()) {
			return showMemberPage(model, principal);
		}

		String username = principal.getName();
		blogService.save(blog, username);

		return "redirect:/member";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.POST)
	public String addPost(Model model, @Valid @ModelAttribute("post") Post post, BindingResult result,
			Principal principal) {

		if (result.hasErrors()) {
			return showMemberPage(model, principal);
		}

		postService.save(post);

		return "redirect:/member";
	}

}
