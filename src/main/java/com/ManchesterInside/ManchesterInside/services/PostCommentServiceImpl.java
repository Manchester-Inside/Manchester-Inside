package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManchesterInside.ManchesterInside.entities.PostComment;
import com.ManchesterInside.ManchesterInside.repositories.PostCommentRepository;

@Service
public class PostCommentServiceImpl implements PostCommentService {
	
	@Autowired
	private PostCommentRepository postCommentRepository;

	@Override
	public long count() {
		return postCommentRepository.count();
	}

	@Override
	public Iterable<PostComment> findAll() {
		return postCommentRepository.findAll();
	}

	@Override
	public Optional<PostComment> findById(long id) {
		return postCommentRepository.findById(id);
	}

	@Override
	public PostComment save(PostComment postcomment) {
		return postCommentRepository.save(postcomment);
	}
	
	@Override
	public void deleteById(long id) {
		postCommentRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll() {
		postCommentRepository.deleteAll();
	}
}
