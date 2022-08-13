package com.ManchesterInside.ManchesterInside.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "Please enter your first name.")
	private String firstName;
	
	@NotEmpty(message = "Please enter your last name.")
	private String lastName;
	
	@NotEmpty(message = "Please enter your username.")
	private String userName;
	
	@NotEmpty(message = "Please enter your password.")
	private String password;
	
	@NotEmpty(message = "Please enter your email.")
	private String email;
	
	private boolean enabled;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = false)
	private List<Post> posts;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
