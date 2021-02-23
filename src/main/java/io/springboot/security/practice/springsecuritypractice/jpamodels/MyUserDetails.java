package io.springboot.security.practice.springsecuritypractice.jpamodels;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
	
	private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;
	
	public MyUserDetails(){
		
	}
	
	public MyUserDetails(User user){
		System.out.println("*****ROLES******** "+user.getRoles());
		this.userName=user.getUserName();
		this.active=user.isActive();
		this.password=user.getPassword();
		//this.authorities=Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(str->new SimpleGrantedAuthority(str))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

}
