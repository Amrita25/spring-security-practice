package io.springboot.security.practice.springsecuritypractice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 UserDetailsService userDetailsService;
	//Authentication
	/*@Override
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
	}*/
	//Authentication using Jpa
	 
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 System.out.println("inside configure method for authentication");
	        auth.userDetailsService(userDetailsService);
	    }
	//Authorization
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                .antMatchers("/admin").hasRole("ADMIN")
	                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
	                .antMatchers("/").permitAll()
	                .and().formLogin();
	    }

	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
		//this is deprecated . this does nothing. not recommended to use this Noop in real lyf scenario
	}

}
