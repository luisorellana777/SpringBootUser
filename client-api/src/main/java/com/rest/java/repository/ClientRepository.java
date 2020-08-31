package com.rest.java.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.rest.java.model.Client;
		
public interface ClientRepository extends JpaRepository<Client, Integer>{

	public Collection<Client> getByUserName(@Param("userName") String userName);
}
