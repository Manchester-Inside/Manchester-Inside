package com.ManchesterInside.ManchesterInside.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ManchesterInside.ManchesterInside.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByuserName(String userName);
}