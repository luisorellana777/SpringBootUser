package com.rest.java.userphoneapi.userphoneapi.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	protected Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "created")
	private Date created;
	
	@Column(name = "updated", insertable=false, updatable=false)
	private Date updated;
	
	@Column(name = "lastLogin")
	private Date lastLogin;
	
	@Column(name = "isActive")
	private Boolean isActive = Boolean.TRUE;
	
	@Column(name = "token", length = 100000)
	private String token;

	public User() {}
	
	public User(String name, String lastName, String email, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Phone> phone = new ArrayList<>();

	public Integer getId() {
		return id;
	}

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

	public void setLastLogin() {
		this.lastLogin = new Date();
	}

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
	
	public com.rest.java.userphoneapi.userphoneapi.dto.User transformUser(){
		
		com.rest.java.userphoneapi.userphoneapi.dto.User user = new com.rest.java.userphoneapi.userphoneapi.dto.User();
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
		
		List<com.rest.java.userphoneapi.userphoneapi.dto.Phone> phones = new ArrayList<com.rest.java.userphoneapi.userphoneapi.dto.Phone>();
		this.getPhone().parallelStream().forEach(phone->{
			com.rest.java.userphoneapi.userphoneapi.dto.Phone phoneModel = new com.rest.java.userphoneapi.userphoneapi.dto.Phone();
			phoneModel.setCitycode(phone.getCitycode());
			phoneModel.setContrycode(phone.getContrycode());
			phoneModel.setId(id);
			phoneModel.setNumber(phone.getNumber());
			
			phones.add(phoneModel);
    	});
		user.setPhone(phones);
		
		return user;
	}
	

}
