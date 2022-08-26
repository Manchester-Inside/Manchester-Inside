package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import com.ManchesterInside.ManchesterInside.entities.User;

public interface UserService {
	
	public long count();
	
	public Iterable<User> findAll();
	
	public Optional<User> findById(long id);
	
	public Optional<User> findByName(String userName);
	
	public User save(User user);
	
	public void updateUser(User user, long id);
}
