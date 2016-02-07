package com.blog.app.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Post;
import com.blog.app.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> getPosts() {
		return postRepository.findAll(new PageRequest(0, 10, Direction.DESC, "created")).getContent();
	}

	public Set<Post> findByBlog(Blog blog) {
		return postRepository.findByBlog(blog);
	}

	public Post findOne(int id) {
		return postRepository.findOne(id);
	}

	public Post findByName(String name) {
		return postRepository.findByName(name);
	}

	public void save(Post post) {
		post.setCreated(new Date());
		postRepository.save(post);
	}
	
	public void update(Post post){
		postRepository.save(post);
	}
	
	@PreAuthorize("#post.blog.member.username == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Post post) {
		postRepository.delete(post);
	}

}
