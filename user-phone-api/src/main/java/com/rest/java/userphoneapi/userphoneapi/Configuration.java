package com.rest.java.userphoneapi.userphoneapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("user-phone")
public class Configuration {

	private String cc;
	
	public Configuration() {
		super();
	}
	
	public Configuration(String cc) {
		super();
		this.cc = cc;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
	

}
