package com.rest.java.userphoneapi.userphoneapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rest.java.userphoneapi.userphoneapi.dto.Phone;
import com.rest.java.userphoneapi.userphoneapi.dto.User;
import com.rest.java.userphoneapi.userphoneapi.service.UserPhoneService;

@RestController
@RequestMapping(path = "/user")
public class UserPhoneControler {
	
	@Autowired
	UserPhoneService userPhoneService;
		
	@PostMapping(path="/")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user)
	{
		return this.userPhoneService.createUser(user);
	}
	
	@GetMapping(path="/")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveUsers")
	public ResponseEntity<Object> getAllUsers()
	{
		return this.userPhoneService.getAllUsers();
	}
	
	public ResponseEntity<Object> fallbackRetrieveUsers()
	{
		return this.userPhoneService.fallbackRetrieveUsers();
	} 

	@PutMapping(path="/login")
	public ResponseEntity<String> login(@Valid @RequestParam("email") String email, @Valid @RequestParam("password") String password) {

		return this.userPhoneService.login(email, password);
	}
	
	@PutMapping(path="/active")
	public ResponseEntity<String> active(@Valid @RequestParam("email") String email, @RequestParam("isActive") Boolean isActive) {
	
		return this.userPhoneService.active(email, isActive);
	}
	
	@PutMapping(path="/")
	public ResponseEntity<String> editUser(@Valid @RequestBody User user) {
		
		return this.userPhoneService.editUser(user);
	}
	
	@GetMapping(path="/pagination")
	public ResponseEntity<Object> getPagination(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize)
	{
		return this.userPhoneService.getAllUsers(pageNumber, pageSize);
	}
	
	@GetMapping(path="/{email}/phone")
	public ResponseEntity<Object> getPhone(@PathVariable String email)
	{
		return this.userPhoneService.getPhone(email);
	}
	
	@PostMapping(path="/{email}/phone")
	public ResponseEntity<Object> createPhone(@PathVariable String email, @RequestBody Phone phone)
	{
		
		return this.userPhoneService.createPhone(email, phone);
	}
}
