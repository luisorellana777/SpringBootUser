package com.rest.java.userphoneapi.userphoneapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phone")
public class Phone implements java.io.Serializable {

	private static final long serialVersionUID = 4773229781031709267L;
	
    @Id
    @GeneratedValue
	@Column(name = "id")
	protected Integer id;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "citycode")
	private Integer citycode;
	
	@Column(name = "contrycode")
	private Integer contrycode;
	
	public Phone(String number, Integer citycode, Integer contrycode) {
		super();
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	public Phone() {}

	@JsonIgnore
	public Integer getId() {
		return id;
	}

	@JsonIgnore
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCitycode() {
		return citycode;
	}

	public void setCitycode(Integer citycode) {
		this.citycode = citycode;
	}

	public Integer getContrycode() {
		return contrycode;
	}

	public void setContrycode(Integer contrycode) {
		this.contrycode = contrycode;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}
	@JsonIgnore
	public void setUser(User user) {
		this.user = user;
	}
	
}
