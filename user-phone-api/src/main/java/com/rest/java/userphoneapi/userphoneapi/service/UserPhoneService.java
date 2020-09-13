package com.rest.java.userphoneapi.userphoneapi.service;

import org.springframework.http.ResponseEntity;

import com.rest.java.userphoneapi.userphoneapi.dto.Phone;
import com.rest.java.userphoneapi.userphoneapi.dto.User;

public interface UserPhoneService {

	ResponseEntity<String> active(String email, Boolean isActive);
	
	ResponseEntity<String> createUser(User user);
    
    ResponseEntity<String> login(String email, String password);
    
    ResponseEntity<Object> getAllUsers();
    
    ResponseEntity<Object> fallbackRetrieveUsers();

    ResponseEntity<String> editUser(User user);

    ResponseEntity<Object> getAllUsers(int pageNumber, int pageSize);
    
    ResponseEntity<Object> getPhone(String email);

	ResponseEntity<Object> createPhone(String email, Phone phone);

}
