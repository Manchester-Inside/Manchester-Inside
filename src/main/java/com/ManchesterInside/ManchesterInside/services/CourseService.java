package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import com.ManchesterInside.ManchesterInside.entities.Course;

public interface CourseService {
	
	public long count();
	
	public Iterable<Course> findAll();
	
	public Optional<Course> findById(long id);
	
	public Course save(Course Course);
	
	public void updateCourse(Course Course, long id);
}
