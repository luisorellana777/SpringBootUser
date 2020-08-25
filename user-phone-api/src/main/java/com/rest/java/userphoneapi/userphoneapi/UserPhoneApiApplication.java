package com.rest.java.userphoneapi.userphoneapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rest.java.userphoneapi.userphoneapi.token.JWTAuthorizationFilter;


@SpringBootApplication
public class UserPhoneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPhoneApiApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.PUT, "/user/login").permitAll()
				.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
				.antMatchers(HttpMethod.GET, "/webjars").permitAll()
				.antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/browser/**",
                        "/actuator/**",
                        "/h2-console/**").permitAll()
				.anyRequest().authenticated();
		}
	}
}