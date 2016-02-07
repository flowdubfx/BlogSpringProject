package com.blog.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entity.Comment;
import com.blog.app.entity.Member;
import java.util.List;
import com.blog.app.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByMember(Member member);

	List<Comment> findByPost(Post post);

}
