package com.springboot.basicauthentication;

import com.springboot.basicauthentication.dto.CreateUserRequest;
import com.springboot.basicauthentication.model.Role;
import com.springboot.basicauthentication.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class BasicAuthenticationApplication implements CommandLineRunner {

	private final UserService userService;

	public BasicAuthenticationApplication(UserService userService) {
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(BasicAuthenticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.name("John")
				.username("jhn")
				.password("pass1")
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		userService.CreateUser(createUserRequest);

		CreateUserRequest createUserRequest2 = CreateUserRequest.builder()
				.name("Mario")
				.username("mro")
				.password("pass2")
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();
		userService.CreateUser(createUserRequest2);

		CreateUserRequest createUserRequest3 = CreateUserRequest.builder()
				.name("Yumer")
				.username("yro")
				.password("pass3")
				.authorities(Set.of(Role.ROLE_MOD))
				.build();
		userService.CreateUser(createUserRequest3);
	}
}
