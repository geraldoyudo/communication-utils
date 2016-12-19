/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.util.MimeMessageParser;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gerald
 */
@Component
public class TestEmailSender implements IEmailSender{

    @Override
    public void send(MimeMessage message) {
        try {
            MimeMessageParser helper = new MimeMessageParser(message);
            System.out.printf("To: %s\n", helper.getTo());
            System.out.printf("From: %s\n", helper.getFrom());
            System.out.printf("Subject: %s\n", helper.getSubject());
            System.out.println(helper.parse().getHtmlContent());
        } catch (Exception ex) {
            System.out.println("Could not read message");
        }
    }
    
}
