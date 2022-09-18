package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import com.ManchesterInside.ManchesterInside.entities.School;

public interface SchoolService {
	
	public long count();
	
	public Iterable<School> findAll();
	
	public Optional<School> findById(long id);
	
	public School save(School Course);
	
	public void updateCourse(School Course, long id);
}
