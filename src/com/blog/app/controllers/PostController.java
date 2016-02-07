package com.blog.app.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blog.app.entity.Comment;
import com.blog.app.entity.Member;
import com.blog.app.entity.Post;
import com.blog.app.service.CommentService;
import com.blog.app.service.MemberService;
import com.blog.app.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private MemberService memberService;

	@ModelAttribute("comment")
	public Comment constructComment() {
		return new Comment();
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	public String showPost(Model model, @PathVariable int id) {

		Post post = postService.findOne(id);
		List<Comment> comments = commentService.findByPost(post);

		model.addAttribute("post", post);
		model.addAttribute("comments", comments);

		return "post";
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
	public String addComment(Model model, @Valid @ModelAttribute("comment") Comment comment, BindingResult result,
			Principal principal, @PathVariable int id) {

		if (result.hasErrors()) {
			return showPost(model, id);
		}

		String username = principal.getName();
		Member member = memberService.findByUsername(username);
		Post post = postService.findOne(id);

		comment.setCreated(new Date());
		comment.setMember(member);
		comment.setPost(post);

		commentService.save(comment);

		return "redirect:/post/" + id;
	}

}
