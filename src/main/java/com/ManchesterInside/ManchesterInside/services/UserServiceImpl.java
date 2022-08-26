package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManchesterInside.ManchesterInside.entities.User;
import com.ManchesterInside.ManchesterInside.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void updateUser(User user, long id) {
		User userInDB = userRepository.findById(id).get();
		userInDB.setFirstName(user.getFirstName());
		userInDB.setLastName(user.getLastName());
		userInDB.setUserName(user.getUserName());
		userInDB.setPassword(user.getPassword());
		userInDB.setEmail(user.getEmail());
		userInDB.setPosts(user.getPosts());
		userInDB.setEnabled(user.isEnabled());
//		userInDB.setRoles(user.getRoles());
		userRepository.save(userInDB);
	}

	@Override
	public Optional<User> findByName(String userName) {
		return userRepository.findByuserName(userName);
	}



}
