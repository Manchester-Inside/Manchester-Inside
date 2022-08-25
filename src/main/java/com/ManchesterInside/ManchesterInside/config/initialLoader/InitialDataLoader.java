package com.ManchesterInside.ManchesterInside.config.initialLoader;

import java.time.LocalDateTime;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ManchesterInside.ManchesterInside.entities.User;
import com.ManchesterInside.ManchesterInside.services.UserService;
import com.ManchesterInside.ManchesterInside.entities.Post;
import com.ManchesterInside.ManchesterInside.services.PostService;
@Configuration
@Profile("default")
public class InitialDataLoader {
	
	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	
	@Bean
	CommandLineRunner initDatabase() {
		return args ->{
			// populate db with users
			if(userService.count() > 0) {
				log.info("Database is already populated with users.");
			}else {
				User user = new User();
				user.setFirstName("Minsung");
				user.setLastName("Kang");
				// very cheeky, william
				user.setUserName("prof-kang");
				user.setPassword("kkk");
				user.setEmail("sample@email.com");
				userService.save(user);
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
