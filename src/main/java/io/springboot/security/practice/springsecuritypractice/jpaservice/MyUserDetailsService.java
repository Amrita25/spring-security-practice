package io.springboot.security.practice.springsecuritypractice.jpaservice;

import java.util.Optional;

import io.springboot.security.practice.springsecuritypractice.jpamodels.MyUserDetails;
import io.springboot.security.practice.springsecuritypractice.jpamodels.User;
import io.springboot.security.practice.springsecuritypractice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user= repo.findByUserName(userName);
		user.orElseThrow(()->new UsernameNotFoundException("No username Found : "+userName));
		return new MyUserDetails(user.get());
	}

}
