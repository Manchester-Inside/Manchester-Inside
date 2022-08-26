package com.ManchesterInside.ManchesterInside.config.initialLoader;

import java.time.LocalDateTime;
import java.util.Set;


import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ManchesterInside.ManchesterInside.entities.Role;
import com.ManchesterInside.ManchesterInside.entities.User;
import com.ManchesterInside.ManchesterInside.services.RoleService;
import com.ManchesterInside.ManchesterInside.services.UserService;
import com.ManchesterInside.ManchesterInside.entities.Post;
import com.ManchesterInside.ManchesterInside.services.PostService;
@Configuration
@Profile("default")
public class InitialDataLoader {
	
	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
  @Autowired
	private RoleService roleService;

	
	@Bean
	CommandLineRunner initDatabase() {
		return args ->{
			// populate db with users
			if(userService.count() > 0) {
				log.info("Database is already populated with users.");
			}else {
				
				Role r1 = new Role();
				r1.setName("ADMIN");
				
				Role r2 = new Role();
				r2.setName("USER");
				
				roleService.save(r1);
				roleService.save(r2);
				
				User user = new User();
				user.getRoles().add(r1);
				user.getRoles().add(r2);
				user.setFirstName("Minsung");
				user.setLastName("Kang");
				// very cheeky, william
				user.setUserName("prof-kang");
				user.setPassword(this.passwordEncoder.encode("i-am-a-professor"));
				user.setEmail("sample@email.com");
				user.setEnabled(true);
				
				User user2 = new User();
				user2.getRoles().add(r2);
				user2.setFirstName("william");
				user2.setLastName("aung");
				user2.setUserName("aaa");
				user2.setPassword(this.passwordEncoder.encode("aaa"));
				user2.setEmail("w.a@gmail.com");
				user2.setEnabled(true);
				
				
				userService.save(user);
				userService.save(user2);
			}
			// populate db with categories
			// TODO: Implement categories and initialize here
			
			// populate db with posts
			if(postService.count() > 0) {
				log.info("Database is already populated with posts.");
			}else {
				Post post1 = new Post();
				// TODO: set category of post
				// post.setCategory();
				post1.setTitle("Test Post Title");
				post1.setContent("Test Content");
				post1.setLastEdited(LocalDateTime.now());
				post1.setTimeUploaded(LocalDateTime.now());
				post1.setUser(userService.findById(1).orElse(null));
				postService.save(post1);
				Post post2 = new Post();
				post2.setTitle("Test Post Title 222");
				post2.setContent("Test Content 222");
				post2.setLastEdited(LocalDateTime.now().plusDays(2));
				post2.setTimeUploaded(LocalDateTime.now().plusDays(1));
				post2.setUser(userService.findById(1).orElse(null));
				postService.save(post2);
			}
			
		};
	}
}
