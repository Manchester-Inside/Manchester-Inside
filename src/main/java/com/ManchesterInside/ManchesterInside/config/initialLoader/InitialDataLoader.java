package com.ManchesterInside.ManchesterInside.config.initialLoader;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ManchesterInside.ManchesterInside.entities.User;
import com.ManchesterInside.ManchesterInside.services.UserService;
@Configuration
@Profile("default")
public class InitialDataLoader {
	
	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);
	
	@Autowired
	private UserService userService;
	
	
	@Bean
	CommandLineRunner initDatabase() {
		return args ->{
			if (userService.count() > 0) {
				log.info("Database is already populated with users.");
			}else {
				User user = new User();
				user.setFirstName("Minsung");
				user.setLastName("Kang");
				user.setUserName("prof-kang");
				user.setPassword("kkk");
				user.setEmail("sample@email.com");
				userService.save(user);
			}
		};
	}
}
