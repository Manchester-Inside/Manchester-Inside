package com.ManchesterInside.ManchesterInside.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="post_comments")
public class PostComment {
	
	@Id
	@Column(name = "post_comment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime timeUploaded;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime lastEdited;
	
	private String comment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Post post;
	
	@ManyToOne(fetch=FetchType.LAZY)
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
