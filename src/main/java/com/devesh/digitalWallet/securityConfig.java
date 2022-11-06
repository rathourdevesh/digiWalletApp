package com.devesh.digitalWallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.devesh.digitalWallet.filters.jwtAuthFilter;
import com.devesh.digitalWallet.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailService;

	@Autowired
	private jwtAuthFilter jwtRequestFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/register").permitAll()
		.anyRequest()
		.authenticated()
		.and().httpBasic()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);;
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/register");
//  	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication()
		//.withUser("foo").password("foo").roles("USER");
		auth.userDetailsService(myUserDetailService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//		  return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
}
