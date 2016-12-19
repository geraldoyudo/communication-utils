/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gerald
 */
@Component("emailSender")
@Profile("production")
@Primary
public class EmailSender implements IEmailSender{
	@Autowired
    private JavaMailSender mailsender;    
    
    @Override
    public void send(MimeMessage message) {
    	try{
    		mailsender.send(message);
    	}catch(MailException ex){
    		ex.printStackTrace();
    	}
    }
    
}
