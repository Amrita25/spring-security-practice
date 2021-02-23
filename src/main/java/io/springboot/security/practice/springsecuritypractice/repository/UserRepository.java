package io.springboot.security.practice.springsecuritypractice.repository;

import java.util.Optional;

import io.springboot.security.practice.springsecuritypractice.jpamodels.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUserName(String userName);

}
