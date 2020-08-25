package com.rest.java.userphoneapi.userphoneapi.dto;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Phone implements java.io.Serializable {

	private static final long serialVersionUID = 4773229781031709267L;
	
	protected Integer id;
	private String number;
	private Integer citycode;
	private Integer contrycode;
	
	public Phone(String number, Integer citycode, Integer contrycode) {
		super();
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}
	
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
	public com.rest.java.userphoneapi.userphoneapi.model.Phone transformPhone(){
		
		com.rest.java.userphoneapi.userphoneapi.model.Phone phoneModel = new com.rest.java.userphoneapi.userphoneapi.model.Phone();
		phoneModel.setCitycode(this.getCitycode());
		phoneModel.setContrycode(this.getContrycode());
		phoneModel.setId(this.id);
		phoneModel.setNumber(this.getNumber());
		
		return phoneModel;
	}
}
