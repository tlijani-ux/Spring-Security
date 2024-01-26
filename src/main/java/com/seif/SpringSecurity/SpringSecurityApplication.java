package com.seif.SpringSecurity;

import com.seif.SpringSecurity.entities.Role;
import com.seif.SpringSecurity.entities.User;
import com.seif.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	public void run(String... args){
		User adminAccount=userRepository.findByRole(Role.ADMIN);
		if(adminAccount==null){

			User user=new User();
			user.setEmail("seif@gmail.com");
			user.setFirstname("seif");
			user.setLastname("seif");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("seif14"));
			userRepository.save(user);

		}

	}



}
