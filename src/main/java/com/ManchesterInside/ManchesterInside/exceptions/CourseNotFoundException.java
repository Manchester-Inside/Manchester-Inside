package com.ManchesterInside.ManchesterInside.exceptions;

public class CourseNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private long id;

	public CourseNotFoundException(long id) {
		super("Could not find post " + id);

		this.id = id;
	}

	public long getId() {
		return id;
	}
}
