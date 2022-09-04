package com.ManchesterInside.ManchesterInside.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime timeUploaded;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDateTime lastEdited;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	private int upvotes = 0;
	
	private int downvotes = 0;
	
	@OneToMany(mappedBy="post", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<PostComment> postComments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getDownvotes() {
		return downvotes;
	}

	public void setDownvotes(int downvotes) {
		this.downvotes = downvotes;
	}
	
	public List<PostComment> getPostComments(){
		return postComments;
	}
	
	/*
	public void setPostComments(List<PostComment> postComments) {
		this.postComments = postComments;
	}
	*/
	
	public void addPostComment(PostComment comment) {
		this.postComments.add(comment);
	}
}
