package com.rest.java.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {

	@Id
    @GeneratedValue
	private Integer id;
	private String name;
	@Column(name="user_name")
	private String userName;
	private String rut;
	private Long budget;
	@Column(name="budget_date")
	private Date budgetDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public Long getBudget() {
		return budget;
	}
	public void setBudget(Long budget) {
		this.budget = budget;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Client() {
		super();
	}
	public Date getBudgetDate() {
		return budgetDate;
	}
	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}
	public Client(Integer id, String name, String userName, String rut, Long budget, Date budgetDate) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.rut = rut;
		this.budget = budget;
		this.budgetDate = budgetDate;
	}

	public com.rest.java.dto.Client transform(){
		com.rest.java.dto.Client clientDto = new com.rest.java.dto.Client();
		clientDto.setBudget(this.budget);
		clientDto.setBudgetDate(this.budgetDate);
		clientDto.setName(this.name);
		clientDto.setRut(this.rut);
		clientDto.setUserName(this.userName);
		return clientDto;
	}
}
