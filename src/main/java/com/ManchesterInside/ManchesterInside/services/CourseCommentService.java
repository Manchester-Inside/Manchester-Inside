package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import com.ManchesterInside.ManchesterInside.entities.CourseComment;

public interface CourseCommentService {
	
	public long count();
	
	public Iterable<CourseComment> findAll();
	
	public Optional<CourseComment> findById(long id);
	
	public CourseComment save(CourseComment CourseComment);
	
	public boolean existsById(long id);
		
	public void delete(CourseComment CourseComment);

	public void deleteById(long id);
	
	public void deleteAll();
	
	public void deleteAll(Iterable<CourseComment> CourseComment);

	public void deleteAllById(Iterable<Long> ids);
	
	public void updateCourseComment(CourseComment CourseComment, long id);

	
}
