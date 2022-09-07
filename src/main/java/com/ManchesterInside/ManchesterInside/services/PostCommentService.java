package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import com.ManchesterInside.ManchesterInside.entities.PostComment;

public interface PostCommentService {
	
	public long count();
	
	public Iterable<PostComment> findAll();
	
	public Optional<PostComment> findById(long id);
	
	public PostComment save(PostComment comment);
	
	public void deleteById(long id);
	
	public void deleteAll();
}
