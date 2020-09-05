package com.rest.java.eureka.userphoneapi.eurekauserphoneclientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaUserPhoneClientApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaUserPhoneClientApiApplication.class, args);
	}

}
