package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManchesterInside.ManchesterInside.entities.CourseComment;
import com.ManchesterInside.ManchesterInside.repositories.CourseCommentRepository;

@Service
public class CourseCommentServiceImpl implements CourseCommentService {
	
	@Autowired
	private CourseCommentRepository CourseCommentRepository;

	@Override
	public long count() {
		return CourseCommentRepository.count();
	}

	@Override
	public Iterable<CourseComment> findAll() {
		return CourseCommentRepository.findAll();
	}
	
	@Override
	public boolean existsById(long id) {
		return CourseCommentRepository.existsById(id);
	}

	@Override
	public Optional<CourseComment> findById(long id) {
		return CourseCommentRepository.findById(id);
	}

	@Override
	public CourseComment save(CourseComment CourseComment) {
		return CourseCommentRepository.save(CourseComment);
	}
	
	@Override
	public void updateCourseComment(CourseComment CourseComment, long id) {
		// TODO Auto-generated method stub
		CourseComment courseCommentInDB = CourseCommentRepository.findById(id).get();
		//postInDB.setFirstName(user.getFirstName());
		// set stuff here
		CourseCommentRepository.save(courseCommentInDB);
	}
	
	@Override
	public void delete(CourseComment CourseComment) {
		CourseCommentRepository.delete(CourseComment);
	}

	@Override
	public void deleteById(long id) {
		CourseCommentRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		CourseCommentRepository.deleteAll();
	}
	
	@Override
	public void deleteAll(Iterable<CourseComment> CourseComment) {
		CourseCommentRepository.deleteAll(CourseComment);
	}

	@Override
	public void deleteAllById(Iterable<Long> ids) {
		CourseCommentRepository.deleteAllById(ids);
	}


}
