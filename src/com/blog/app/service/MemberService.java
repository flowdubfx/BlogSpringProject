package com.blog.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Member;
import com.blog.app.entity.Post;
import com.blog.app.entity.Role;
import com.blog.app.repository.BlogRepository;
import com.blog.app.repository.MemberRepository;
import com.blog.app.repository.PostRepository;
import com.blog.app.repository.RoleRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private PostRepository postRepository;
	
	

	public void saveMember(Member member) {

		member.setEnabled(true);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		member.setPassword(encoder.encode(member.getPassword()));

		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findByName("ROLE_USER"));

		member.setRoles(roles);

		memberRepository.save(member);
	}

	public Member findByUsername(String username) {
		return memberRepository.findByUsername(username);
	}

	public Member findOne(int id) {
		return memberRepository.findOne(id);
	}

	public Member findOneWithBlogs(int id) {
		Member member = findOne(id);
		List<Blog> blogs = blogRepository.findByMember(member);
		for (Blog blog : blogs) {
			Set<Post> posts = postRepository.findByBlog(blog);
			blog.setPosts(posts);
		}
		member.setBlogs(blogs);

		return member;
	}

	public Member findOneWithBlogs(String username) {
		Member member = memberRepository.findByUsername(username);
		return findOneWithBlogs(member.getId());
	}

	public List<Member> getMembers() {
		return memberRepository.findAll();
	}
	
	

}
