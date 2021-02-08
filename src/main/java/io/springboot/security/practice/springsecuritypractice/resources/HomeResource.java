package io.springboot.security.practice.springsecuritypractice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	
	//should be accessible by everybody even when they have not logged in 
	@GetMapping("/")
	public String home(){
		return ("<h1>Welcome!!</h1>");
	}
	//should be accessible by only admins
	@GetMapping("/admin")
	public String adminHome(){
		return ("<h1>Welcome Admin !!</h1>");
	}
	//should be accessible by logged in users(user and admin both)
	@GetMapping("/user")
	public String userHome(){
		return ("<h1>Welcome App User!!</h1>");
	}

}
