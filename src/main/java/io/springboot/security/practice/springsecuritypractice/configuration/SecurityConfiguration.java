package io.springboot.security.practice.springsecuritypractice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		//auth.userDetailsService(userDetailsService)
		
		auth.inMemoryAuthentication()
		.withUser("foo")
		.password("foo")
		.roles("USER")
		.and()
		.withUser("amrita")
		.password("amrita")
		.roles("ADMIN");
	}
	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	//All urls of this application will only be accessible by users with ADMIN role. Login type is FormLogin
		http.authorizeRequests()
		.antMatchers("/**").hasRole("ADMIN")
		.and().formLogin();
	
	/**
	 * "/admin" should be accessible by only admins
	 * "/user" should be accessible by logged in users(user and admin both)
	 * "/" should be accessible by everybody even when they have not logged in 
	 * Configure this in a way to follow  Most restrictive to Lease restrictive order.
	 */
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/").permitAll()
		.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
		//this is deprecated . this does nothing. not recommended to use this Noop in real lyf scenario
	}

}
