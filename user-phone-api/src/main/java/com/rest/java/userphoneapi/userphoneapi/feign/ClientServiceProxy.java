package com.rest.java.userphoneapi.userphoneapi.feign;

import java.util.Collection;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rest.java.userphoneapi.userphoneapi.dto.Client;

@RibbonClient(name="client-api")
@FeignClient(name = "client-api")
public interface ClientServiceProxy {
	
	@GetMapping("/client/{userName}")
	public Collection<Client> getByUserName(@PathVariable String userName);
}