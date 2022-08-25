package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManchesterInside.ManchesterInside.entities.Post;
import com.ManchesterInside.ManchesterInside.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public long count() {
		return postRepository.count();
	}

	@Override
	public Iterable<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Optional<Post> findById(long id) {
		return postRepository.findById(id);
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public void updatePost(Post post, long id) {
		Post postInDB = postRepository.findById(id).get();
		//postInDB.setFirstName(user.getFirstName());
		// set stuff here
		postRepository.save(postInDB);
	}

}
