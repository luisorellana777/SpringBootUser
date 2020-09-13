package com.rest.java.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.java.dto.Client;
import com.rest.java.exception.ClientNotFoundException;
import com.rest.java.repository.ClientRepository;
import com.rest.java.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public Collection<Client> getByUserName(String userName) {
		Collection<Client> clients = new ArrayList<Client>();
		Collection<com.rest.java.model.Client> clientsModel = clientRepository.getByUserName(userName);
		if(clientsModel.isEmpty())throw new ClientNotFoundException("Cliente no Encontrado");
		clientsModel.forEach(clientModel -> {
			clients.add(clientModel.transform());
		});
		
		return clients;
	}

}
