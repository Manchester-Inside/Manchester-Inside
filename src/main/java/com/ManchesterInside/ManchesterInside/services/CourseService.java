package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import com.ManchesterInside.ManchesterInside.entities.Course;

public interface CourseService {
	
	public long count();
	
	public Iterable<Course> findAll();
	
	public Optional<Course> findById(long id);
	
	public Course save(Course Course);
	
	public boolean existsById(long id);
	
	public void updateCourse(Course Course, long id);
	
	public void delete(Course Course);

	public void deleteById(long id);
	
	public void deleteAll();

	public void deleteAll(Iterable<Course> Courses);

	public void deleteAllById(Iterable<Long> ids);


	
}
