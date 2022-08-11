package com.ManchesterInside.ManchesterInside.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ManchesterInside.ManchesterInside.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
