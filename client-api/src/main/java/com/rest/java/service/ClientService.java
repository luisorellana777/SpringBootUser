package com.rest.java.service;

import java.util.Collection;

import com.rest.java.dto.Client;

public interface ClientService {

	Collection<Client> getByUserName(String userName);
}
