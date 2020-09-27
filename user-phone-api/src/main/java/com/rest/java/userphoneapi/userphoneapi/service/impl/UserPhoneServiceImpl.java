package com.rest.java.userphoneapi.userphoneapi.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest.java.userphoneapi.userphoneapi.exception.DataIntegrityException;
import com.rest.java.userphoneapi.userphoneapi.exception.UserNotFoundException;
import com.rest.java.userphoneapi.userphoneapi.feign.ClientServiceProxy;
import com.rest.java.userphoneapi.userphoneapi.model.Phone;
import com.rest.java.userphoneapi.userphoneapi.model.User;
import com.rest.java.userphoneapi.userphoneapi.repository.PhoneRepository;
import com.rest.java.userphoneapi.userphoneapi.repository.UserRepository;
import com.rest.java.userphoneapi.userphoneapi.service.TokenService;
import com.rest.java.userphoneapi.userphoneapi.service.UserPhoneService;

@Service
public class UserPhoneServiceImpl implements UserPhoneService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PhoneRepository phoneRepository;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private ClientServiceProxy clientServiceProxy;

    @Override
    public ResponseEntity<String> createUser(com.rest.java.userphoneapi.userphoneapi.dto.User user) {

    	try {
    		
        	if (!(userRepository.getUserByEmail(user.getEmail()).isEmpty())) {
        		
        		throw new DataIntegrityException("El correo ya registrado");
        	}
        	
        	userRepository.save(user.transformUser());
        	
            return new ResponseEntity<>(
    	  	          "{ \"mensaje\" : \"Usuario Creado\" }", 
    	  	          HttpStatus.ACCEPTED);
            
    	}catch(Exception ex){
    		
    		throw new DataIntegrityException(ex.getMessage());
    	}

    }

    @Override
    public ResponseEntity<String> editUser(com.rest.java.userphoneapi.userphoneapi.dto.User user) {
    	
		try {
			List<User> users = (List<User>) userRepository.getUserByEmail(user.getEmail());
			
			if(users.isEmpty()) {
				
				return new ResponseEntity<>(
				          "{ \"mensaje\" : \"Usuario(Email) No Encontrado\" }", 
				          HttpStatus.NOT_FOUND);
			}
			
			user.setId(users.get(0).getId());
			userRepository.save(user.transformUser());
			return new ResponseEntity<>(
			          "{ \"mensaje\" : \"Usuario Editado\" }", 
			          HttpStatus.CREATED);
		}catch(Exception ex){
	
			throw new DataIntegrityException(ex.getMessage());
		}
        
    }
    
    public ResponseEntity<String> editUser(User user) {
    	
		try {
			List<User> users = (List<User>) userRepository.getUserByEmail(user.getEmail());
			
			if(users.isEmpty()) {
				
				return new ResponseEntity<>(
				          "{ \"mensaje\" : \"Usuario(Email) No Encontrado\" }", 
				          HttpStatus.NOT_FOUND);
			}
			
			user.setId(users.get(0).getId());
			userRepository.save(user);
			return new ResponseEntity<>(
			          "{ \"mensaje\" : \"Usuario Editado\" }", 
			          HttpStatus.CREATED);
		}catch(Exception ex){
	
			throw new DataIntegrityException(ex.getMessage());
		}
        
    }

    @Override
    public ResponseEntity<Object> getAllUsers(int pageNumber, int pageSize) {
    	
    	try {

		    return new ResponseEntity<>(
		    		//userRepository.findAll(new PageRequest(pageNumber, pageSize)).getContent(),
		    		null,
			          HttpStatus.OK);
		    
		}catch(Exception ex){
			
			throw new DataIntegrityException(ex.getMessage());
		}
    }

    @Override
    public ResponseEntity<Object> getAllUsers() {
    	
    	try {
    		List<com.rest.java.userphoneapi.userphoneapi.dto.User> usersDto = new ArrayList<com.rest.java.userphoneapi.userphoneapi.dto.User>();
		    List<User> users = userRepository.findAll();
		    users.forEach(user -> {
		    	com.rest.java.userphoneapi.userphoneapi.dto.User userDto = user.transformUser();
		    	userDto.setClients(clientServiceProxy.getByUserName(user.getName()));
		    	usersDto.add(userDto);
		    	
		    });
			return new ResponseEntity<>(
					usersDto, 
			          HttpStatus.OK);
        
		}catch(Exception ex){
			
		    throw new DataIntegrityException(ex.getMessage());
		}
    }

	@Override
	public ResponseEntity<String> login(String email, String password) {
		
		try {
			List<User> users = (List<User>) userRepository.login(email, password);
		    if (users.isEmpty()) {
		    	throw new UserNotFoundException("Usuario(Email) o Clave No Encontrado");
		        
		    } else {
		    	
		    	User user = users.get(0);
		    	user.setLastLogin(LocalDateTime.now());
		    	String token = tokenService.getJWTToken(user.getEmail());
		    	user.setToken(token);
		    	
		    	this.editUser(user);
		        return new ResponseEntity<>(
		  	          "{ \"mensaje\" : \"Login\","
		  	          + " \"token\" : \""+token+"\"}",
		  	          HttpStatus.ACCEPTED);
		    }
	    
    	}catch(Exception ex){
    		
    		throw new DataIntegrityException(ex.getMessage());
    	}

	}

	@Override
	public ResponseEntity<String> active(String email, Boolean isActive) {
		
		try {
			if ((userRepository.getUserByEmail(email).isEmpty())) {
				
				throw new UserNotFoundException("Usuario(Email) No Encontrado");
			}
			
			userRepository.updateIsActiveByEmail(email, isActive);
			
	        return new ResponseEntity<>(
		  	          "{ \"mensaje\" : \"Usuario Modificado\"}",
		  	          HttpStatus.ACCEPTED);
        
    	}catch(Exception ex){
    		
    		throw new DataIntegrityException(ex.getMessage());
    	}
	}

	@Override
	public ResponseEntity<Object> getPhone(String email) {
		
		try {
			
			List<User> users = (List<User>) userRepository.getUserByEmail(email);
			
			if(users.isEmpty()) {
				
				throw new UserNotFoundException("Usuario(Email) No Encontrado");
			}
			
		    return new ResponseEntity<>(
		    		users.get(0).getPhone(), 
			          HttpStatus.OK);
    	}catch(Exception ex){
    		
    		throw new DataIntegrityException(ex.getMessage());
    	}

	}

	@Override
	public ResponseEntity<Object> createPhone(String email, com.rest.java.userphoneapi.userphoneapi.dto.Phone phone) {
		
		try {
			
			List<User> users = (List<User>) userRepository.getUserByEmail(email);
			
			if(users.isEmpty()) {
				
				throw new UserNotFoundException("Usuario(Email) No Encontrado");
			}
			
			Phone phoneModel = phone.transformPhone();
			phoneModel.setUser(users.get(0));
			
			phoneRepository.save(phoneModel);
	        return new ResponseEntity<>(
		  	          "{ \"mensaje\" : \"Usuario Modificado\"}",
		  	          HttpStatus.ACCEPTED);
	        
    	}catch(Exception ex){
    		
    		throw new DataIntegrityException(ex.getMessage());
    	
		}

	}

	@Override
	public ResponseEntity<Object> fallbackRetrieveUsers() {
		
		return new ResponseEntity<>(
				new com.rest.java.userphoneapi.userphoneapi.dto.Client(
						"defaultName", 
						"defaultUserName", 
						"11111111-1", 
						1L, 
						new Date()), 
		          HttpStatus.PARTIAL_CONTENT);
	}
}
