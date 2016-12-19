package com.gerald.utils.communication;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gerald.utils.communication.entities.SMSMessage;

@Component("smsSender")
@Profile("production")
@Primary
public class RestApiSMSSender implements ISMSSender{
	@Value("${app.sms-service.url}")
	private String restApiUrl;
	@Value("${app.sms-service.http-method:post}")
	private String httpMethod;
	@Value("${app.sms-service.keys.to:to}")
	private String toKey = "to";
	@Value("${app.sms-service.keys.from:from}")
	private String fromKey = "from";
	@Value("${app.sms-service.keys.body:body}")
	private String bodyKey = "body";
	@Value("${app.sms-service.auth.username:username}")
	private String username;
	@Value("${app.sms-service.auth.password:password}")
	private String password;
	@Value("${app.sms-service.auth.type:http-basic}")
	private String authType = "http-basic";
	@Value("${app.sms-service.keys.user:username}")
	private String userKey;
	@Value("${app.sms-service.keys.password:password}")
	private String passwordKey;
	@Autowired
	private RestSmsApiConfiguration smsConfigurationMap;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public void send(SMSMessage message) {
		Map<String, String> messageData = new HashMap<>();
		messageData.put(toKey, message.getDestinationPhone());
		messageData.put(fromKey, message.getOriginatingPhone());
		messageData.put(bodyKey, message.getMessage());
		messageData.putAll(smsConfigurationMap.getConfiguration());
		String response = "";
		if(authType.equals("http-basic")){
			String authorization = "Basic " + Base64Utils.encodeToString
					(String.format("%s:%s", username,password).getBytes());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);   
			headers.set("Authorization", authorization);
     		HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(messageData, headers);
     		if(httpMethod.equalsIgnoreCase("get")){
     			response = restTemplate.exchange(restApiUrl, HttpMethod.GET, request, String.class).getBody();
     		}else{
     			response = restTemplate.postForObject(restApiUrl, request, String.class);
     		}
     	
		}else{
			messageData.put(userKey, username);
			messageData.put(passwordKey, password);
			System.out.println(httpMethod);
			if(httpMethod.equalsIgnoreCase("get")){
				MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
				for(Entry<String,String> entry: messageData.entrySet()){
					params.add(entry.getKey(), entry.getValue());
				}
				String uri = UriComponentsBuilder.fromUriString(restApiUrl).queryParams(params).build().toUriString();
				response = restTemplate.getForObject(uri, String.class );
     		}else{
     			response = restTemplate.postForObject(restApiUrl, messageData, String.class);
     		}	
		}
		System.out.println("SMS Data = " + messageData);
		System.out.println("Response = " + response );
		
		
	}



}
