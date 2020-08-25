package com.rest.java.userphoneapi.configserveruserphone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerUserPhoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerUserPhoneApplication.class, args);
	}

}
