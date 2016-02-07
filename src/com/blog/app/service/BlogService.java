package com.blog.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Member;
import com.blog.app.repository.BlogRepository;
import com.blog.app.repository.MemberRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private MemberRepository memberRepository;

	public List<Blog> getBlogs() {
		return blogRepository.findAll();
	}
	
	public List<Blog> findByMember(Member member){
		return blogRepository.findByMember(member);
	}

	public Blog getBlog(int id) {
		return blogRepository.findOne(id);
	}

	public Blog getBlogByName(String name) {
		return blogRepository.getBlogByName(name);
	}

	public void save(Blog blog, String username) {
		Member member = memberRepository.findByUsername(username);
		blog.setMember(member);
		blog.setDateCreated(new Date());
		blogRepository.save(blog);
	}

	public void update(Blog blog) {
		blogRepository.save(blog);
	}
	
	@PreAuthorize("#blog.member.username == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

}
