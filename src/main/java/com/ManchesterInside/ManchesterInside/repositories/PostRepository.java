package com.ManchesterInside.ManchesterInside.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ManchesterInside.ManchesterInside.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	// orders post by descending order of uploaded date
	// Spring magic!
	// enable later
	// Iterable<Post> findAllbyOrderByTimeUploadedDesc();
}
