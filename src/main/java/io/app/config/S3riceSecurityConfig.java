package io.app.config;

import io.app.dto.User;
import io.app.repository.UserRepository;
import io.app.security.UserDetailsServiceImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class S3riceSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Autowired
	private UserDetailsServiceImpl userDetailsService;


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/location.xml").permitAll()
		.antMatchers("/contact-us").permitAll()
		.antMatchers("/offers","/","/otp-login","index.html","/login","/js/**","/images/**","/css/**","/checkTheDue","/due-details").permitAll()
		.anyRequest().hasRole("ADMIN")
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/dashboard")
		.permitAll()
		.and()
		.logout()
		.permitAll()
		.and();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	public void saveUser() {
		System.out.println("Post construcct is invoked");
		User user=new User("SHA473","shaiksha",passwordEncoder.encode("shaiksha"),true);
		userRepository.save(user);
	}
}
