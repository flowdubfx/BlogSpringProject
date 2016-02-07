package com.blog.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entity.Blog;
import com.blog.app.entity.Member;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByMember(Member member);

	Blog getBlogByName(String name);

}
