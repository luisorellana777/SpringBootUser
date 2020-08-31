package com.rest.java.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.java.dto.Client;
import com.rest.java.service.ClientService;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/{userName}")
	public Collection<Client> getByUserName(@PathVariable String userName) {
		return clientService.getByUserName(userName);
	}
}
