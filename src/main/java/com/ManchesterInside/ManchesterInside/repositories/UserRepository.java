package com.ManchesterInside.ManchesterInside.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ManchesterInside.ManchesterInside.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByuserName(String userName);
}