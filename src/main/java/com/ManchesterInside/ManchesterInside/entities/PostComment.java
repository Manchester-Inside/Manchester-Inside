package com.ManchesterInside.ManchesterInside.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="post_comments")
public class PostComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	// Still have to define the format
	private LocalDateTime timeUploaded;
	
	// Still have to define the format
	private LocalDateTime lastEdited;
	
	private String comment;
	
	@ManyToOne
	private Post post;
	
	@OneToOne
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimeUploaded() {
		return timeUploaded;
	}

	public void setTimeUploaded(LocalDateTime timeUploaded) {
		this.timeUploaded = timeUploaded;
	}

	public LocalDateTime getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(LocalDateTime lastEdited) {
		this.lastEdited = lastEdited;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
