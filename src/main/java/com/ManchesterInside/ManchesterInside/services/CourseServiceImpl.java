package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManchesterInside.ManchesterInside.entities.Course;
import com.ManchesterInside.ManchesterInside.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository CourseRepository;

	@Override
	public long count() {
		return CourseRepository.count();
	}

	@Override
	public Iterable<Course> findAll() {
		return CourseRepository.findAll();
	}

	@Override
	public Optional<Course> findById(long id) {
		return CourseRepository.findById(id);
	}

	@Override
	public Course save(Course course) {
		return CourseRepository.save(course);
	}

	public void updateCourse(Course Course, long id) {
		// TODO Auto-generated method stub
		Course courseInDB = CourseRepository.findById(id).get();
		//postInDB.setFirstName(user.getFirstName());
		// set stuff here
		CourseRepository.save(courseInDB);
		
	}


}
