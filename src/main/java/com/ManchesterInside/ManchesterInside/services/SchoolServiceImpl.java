package com.ManchesterInside.ManchesterInside.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ManchesterInside.ManchesterInside.entities.School;
import com.ManchesterInside.ManchesterInside.repositories.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolRepository SchoolRepository;

	@Override
	public long count() {
		return SchoolRepository.count();
	}

	@Override
	public Iterable<School> findAll() {
		return SchoolRepository.findAll();
	}

	@Override
	public Optional<School> findById(long id) {
		return SchoolRepository.findById(id);
	}

	@Override
	public School save(School school) {
		return SchoolRepository.save(school);
	}

	public void updateCourse(School school, long id) {
		// TODO Auto-generated method stub
		School schoolInDB = SchoolRepository.findById(id).get();
		//postInDB.setFirstName(user.getFirstName());
		// set stuff here
		SchoolRepository.save(schoolInDB);
		
	}


}
