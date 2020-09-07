package com.rest.java.userphoneapi.zuuluserphone.zuuluserphone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulUserPhoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulUserPhoneApplication.class, args);
	}

}
