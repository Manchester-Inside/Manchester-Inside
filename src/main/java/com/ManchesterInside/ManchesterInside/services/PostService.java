package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import com.ManchesterInside.ManchesterInside.entities.Post;

public interface PostService {
	
	public long count();
	
	public Iterable<Post> findAll();
	
	public Optional<Post> findById(long id);
	
	public Post save(Post post);
	
	public void updatePost(Post post, long id);
}
