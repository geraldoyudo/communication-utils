package com.gerald.utils.communication;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
@Qualifier("customFormatMimeMailSender")
public class CustomFormatMimeEmailSender implements
Sender<Map<String,Object>> , AsyncSender<Map<String,Object>>{
	@Autowired
	private IEmailSender emailSender;
	@Autowired
	private MessageConstructor<MimeMessageHelper> messageConstructor;
	
	public static final String TO = "_ÉMAIL_TO";
	public static final String FROM = "_EMAIL_FROM";
	public static final String SUBJECT = "_EMAIL_SUBJECT";
	public static final String CC = "_EMAIL_CC";
	public static final String BCC = "_EMAIL_BCC";
	
	public CustomFormatMimeEmailSender() {
		// TODO Auto-generated constructor stub
	}
	public CustomFormatMimeEmailSender(MessageConstructor<MimeMessageHelper> mc
			, IEmailSender emailSender) {
		this.emailSender = emailSender;
		this.messageConstructor = mc;
	}
	
	@Override
	public void send(Map<String, Object> context) {
		MimeMessageHelper m = messageConstructor.constructMessage(context);
		addProperties(m, context);
		emailSender.send(m.getMimeMessage());
		
	}

	private void addProperties(MimeMessageHelper m, Map<String,Object> context) {
		String [] fields = {TO,FROM, SUBJECT, CC, BCC};
		for(String field: fields){
			try{
			apply(field, m, context.get(field));
			}catch(MessagingException ex){
				ex.printStackTrace();
			}
		}
	}
	private void apply(String field, MimeMessageHelper m, Object value) throws MessagingException{
		if(value == null)
			return;
		
		switch(field){
		case TO:{
			if(value instanceof Collection){
				Collection<String> collection = (Collection<String>)value;
				m.setTo(collection.toArray(new String[collection.size()]) );
			}else{
				m.setTo(value.toString());
			}
			return;
		}
		case FROM:{
			m.setFrom(value.toString());
			return;
		}
		case SUBJECT:{
			m.setSubject(value.toString());
			return;
		}
		case BCC:{
			if(value instanceof Collection){
				Collection<String> collection = (Collection<String>)value;
				m.setBcc(collection.toArray(new String[collection.size()]) );
			}else{
				m.setBcc(value.toString());
			}
			return;
		}
		case CC:{
			if(value instanceof Collection){
				Collection<String> collection = (Collection<String>)value;
				m.setCc(collection.toArray(new String[collection.size()]) );
			}else{
				m.setCc(value.toString());
			}
			return;
		}
		default:{
			return;
		}
		}
	}
	@Override
	public Future<Void> sendAsync(Map<String, Object> message) {
		send(message);
		return new AsyncResult<Void>(null);
		
	}
}
