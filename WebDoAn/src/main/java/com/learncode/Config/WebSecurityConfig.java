package com.learncode.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.learncode.Service.UserDetailsServiceImpl;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auProvider = new DaoAuthenticationProvider();
		auProvider.setPasswordEncoder(passwordEncoder());
		auProvider.setUserDetailsService(userDetailsService());
		
		return auProvider;
	}
	
	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
//	public static void main(String[] args) {
//		String password = "12345";
//		String encrytedPassword = encrytePassword(password);
//
//		System.out.println("Encryted password: " + encrytedPassword);
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/", "/css/StyleLogin.css").permitAll();
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/success").permitAll();
		http.logout();
	}
}
