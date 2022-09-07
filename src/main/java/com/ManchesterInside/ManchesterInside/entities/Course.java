package com.ManchesterInside.ManchesterInside.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String courseName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime time;
	
	@NotEmpty
	private String description;
	
	@NotNull
	@ManyToOne
	private School school;
	
	@OneToMany(mappedBy="course", cascade = CascadeType.REMOVE, orphanRemoval = false)
	private List<CourseComment> courseComments;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	public List<CourseComment> getCourseComments(){
		return courseComments;
	}
	
	
	public void addCourseComment(CourseComment comment) {
		this.courseComments.add(comment);
	}
}
