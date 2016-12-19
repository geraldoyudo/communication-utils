package com.gerald.utils.communication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.sms-service")
public class RestSmsApiConfiguration {
	private Map<String,String> configuration = new HashMap<>();
	
	 public Map<String, String> getConfiguration() {
		return configuration;
	}
	 
	 public void setConfiguration(Map<String, String> configuration) {
		this.configuration = configuration;
	}
}
