package com.ManchesterInside.ManchesterInside.config.initialLoader;

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
@Configuration
@Profile("default")
public class InitialDataLoader {
	
	private final static Logger log = LoggerFactory.getLogger(InitialDataLoader.class);
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Bean
	CommandLineRunner initDatabase() {
		return args ->{
			if (userService.count() > 0) {
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
				user.setUserName("prof-kang");
				user.setPassword(this.passwordEncoder.encode("i-am-a-professor"));
				user.setEmail("sample@email.com");
				user.setEnabled(true);
				userService.save(user);
			}
		};
	}
}
