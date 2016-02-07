package com.blog.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entity.Comment;
import com.blog.app.entity.Post;
import com.blog.app.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> findByPost(Post post) {
		return commentRepository.findByPost(post);
	}

	public void save(Comment comment) {
		commentRepository.save(comment);
	}

}
