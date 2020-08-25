package com.rest.java.userphoneapi.userphoneapi.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;

	private String name;

	private String lastName;
	
	@Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$", message = "Email Incorrecto")
	private String email;

	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", message="Password must contains at least one number")
	private String password;

	private Date created;

	private Date updated;

	private Date lastLogin;

	private Boolean isActive = Boolean.TRUE;

	private String token;

	public User() {}
	
	public User(String name, String lastName, String email, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	private List<Phone> phone = new ArrayList<>();

	@JsonIgnore
	public Integer getId() {
		return id;
	}

	@JsonIgnore
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Phone> getPhone() {
		return phone;
	}

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

	public String getCreated() {
		return  Objects.isNull(created) ? null : new SimpleDateFormat("yyyy-MM-dd").format(created);
	}

	public String getUpdated() {
		return Objects.isNull(updated) ? new SimpleDateFormat("yyyy-MM-dd").format(new Date()) : new SimpleDateFormat("yyyy-MM-dd").format(updated);
	}

	public String getLastLogin() {
		return Objects.isNull(lastLogin) ? new SimpleDateFormat("yyyy-MM-dd").format(Objects.isNull(created) ? new Date() : created) : new SimpleDateFormat("yyyy-MM-dd").format(lastLogin);
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@JsonIgnore
	public void setLastLogin() {
		this.lastLogin = new Date();
	}

	protected void onCreate() {
		created = new Date();
	}

	protected void onUpdate() {
		updated = new Date();
	}
	
	public com.rest.java.userphoneapi.userphoneapi.model.User transformUser(){
		
		com.rest.java.userphoneapi.userphoneapi.model.User user = new com.rest.java.userphoneapi.userphoneapi.model.User();
		user.setCreated(this.created);
		user.setEmail(this.email);
		user.setId(this.id);
		user.setIsActive(this.isActive);
		user.setLastLogin();
		user.setLastName(this.lastName);
		user.setName(this.lastName);
		user.setPassword(this.password);
		user.setToken(this.token);
		user.setUpdated(this.updated);
		
		List<com.rest.java.userphoneapi.userphoneapi.model.Phone> phones = new ArrayList<com.rest.java.userphoneapi.userphoneapi.model.Phone>();
		this.getPhone().parallelStream().forEach(phone->{
			com.rest.java.userphoneapi.userphoneapi.model.Phone phoneModel = new com.rest.java.userphoneapi.userphoneapi.model.Phone();
			phoneModel.setCitycode(phone.getCitycode());
			phoneModel.setContrycode(phone.getContrycode());
			phoneModel.setId(id);
			phoneModel.setNumber(phone.getNumber());
			phoneModel.setUser(user);
			
			phones.add(phoneModel);
    	});
		user.setPhone(phones);
		
		return user;
	}

}
