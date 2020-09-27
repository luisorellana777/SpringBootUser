package com.rest.java.userphoneapi.userphoneapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.java.userphoneapi.userphoneapi.model.Phone;
import com.rest.java.userphoneapi.userphoneapi.model.User;

public class AppTest extends UserPhoneApiApplicationTests {

	public static final MediaType APPLICATION_JSON_UTF8 = 
			new MediaType(MediaType.APPLICATION_JSON.getType(), 
					MediaType.APPLICATION_JSON.getSubtype(), 
					Charset.forName("utf8"));
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAll() throws Exception {
		mockMvc.perform(get("/user/all")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$[0].name").value("luis")).andExpect(jsonPath("$[0].lastName").value("orellana"));
	}
	
	@Test
	public void testCreateUser() throws Exception {
		
		List<Phone> phone = new ArrayList<>();
		phone.add(new Phone("12345", 111, 222));
		
		User user = new User("luis", "orellana", "luisss.orellana@mail.com", "Loa33");
		user.setPhone(phone);
		user.setUpdated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);

	    mockMvc.perform(post("/user/").contentType(APPLICATION_JSON_UTF8)
	            .content(userJson))
	            .andExpect(status().isAccepted());
	    
	}
	
	@Test
	public void login() throws Exception {
		mockMvc.perform(put("/user/login").param("email", "luis.orellana@mail.com").param("password", "Loa33")).andExpect(status().isAccepted())
				.andExpect(content().contentType("text/plain;charset=UTF-8"));
	}
	
	@Test
	public void active() throws Exception {
		mockMvc.perform(put("/user/active").param("email", "luis.orellana@mail.com").param("isActive", "false")).andExpect(status().isAccepted())
				.andExpect(content().contentType("text/plain;charset=UTF-8"));
	}
	
	@Test
	public void editUser() throws Exception {
		
		List<Phone> phone = new ArrayList<>();
		phone.add(new Phone("321", 44, 55));
		
		User user = new User("luis", "orellana", "luis.orellana@mail.com", "Loa35");
		user.setPhone(phone);
		user.setUpdated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);

	    mockMvc.perform(put("/user/").contentType(APPLICATION_JSON_UTF8)
	            .content(userJson))
	            .andExpect(status().isCreated());
	    
	}
	
	@Test
	public void getPagination() throws Exception {
		mockMvc.perform(get("/user/pagination").param("pageNumber", "1").param("pageSize", "1")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8));
	}
}