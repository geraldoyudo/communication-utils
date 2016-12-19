package com.gerald.utils.communication;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;



@Configuration
@ComponentScan
public class CommunicationConfig {
	@Value("${communication.mail.username:test}")
	private String username;
	@Value("${communication.mail.subject:Hello World}")
	private String defaultSubject;
	@Value("${commuinication.mail.email:test@email.com}")
	private String email;
	
	@Bean(name = "defaultTemplate")
	@Qualifier("default") 
	@Primary
    public MimeMessage contactUsEmailTemplate(JavaMailSender mailSender) throws MessagingException{
         MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(username);
        helper.setSubject(defaultSubject);
        helper.setTo(email);
        helper.setText("");
        return helper.getMimeMessage();
    }
	
	@Bean(name="appMessageService")
	public MessageSourceAccessor messageSource(MessageSource messageSource){
		return new MessageSourceAccessor(messageSource, Locale.ENGLISH);
	}
	
    
}
