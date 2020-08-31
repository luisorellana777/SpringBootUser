package com.rest.java.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Client {

	@NotEmpty(message = "Nombre no puede ser nulo")
	@NotNull(message = "Nombre no puede ser nulo")
	private String name;
	@NotEmpty(message = "Nombre no puede ser nulo")
	@NotNull(message = "Nombre no puede ser nulo")
	private String userName;
	@Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}[-][0-9kK]{1}$", message = "Formato de Rut Incorrecto")
	private String rut;
	private Long budget;
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
	public Date getBudgetDate() {
		return budgetDate;
	}
	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}
	public Client(String name, String userName, String rut, Long budget, Date budgetDate) {
		super();
		this.name = name;
		this.userName = userName;
		this.rut = rut;
		this.budget = budget;
		this.budgetDate = budgetDate;
	}
	public Client() {
		super();
	}

}
