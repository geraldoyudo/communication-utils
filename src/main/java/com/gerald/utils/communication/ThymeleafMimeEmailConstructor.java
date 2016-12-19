/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Gerald
 */

@Component
public class ThymeleafMimeEmailConstructor implements MessageConstructor<MimeMessageHelper> {
	@Autowired
	private SpringTemplateEngine templateEngine;
	@Getter @Setter
	@Autowired
	@Qualifier("default")
    private MimeMessage defaultMessageTemplate = null;
    @Getter @Setter
	@Value("${commuinication.mail.template:default.html}")
    private String defaultTemplateName = "default.html";
    public static final String HTML_TEMPLATE = "htmlTemplate";
    public static final String MIME_MESSAGE_TEMPLATE = "mimeMessageTemplate";
    

    
    
    @Override
    public MimeMessageHelper constructMessage(Map<String, Object> context) {
        try {
            Context ctx = new Context(Locale.getDefault(), context);
            String currentTemplateName =(String) context.get(HTML_TEMPLATE);
            if(currentTemplateName == null)
            	currentTemplateName = defaultTemplateName;
            MimeMessage currentMessageTemplate = (MimeMessage)context.get(MIME_MESSAGE_TEMPLATE);
            if(currentMessageTemplate == null)
            	currentMessageTemplate = defaultMessageTemplate;
           System.out.println(currentMessageTemplate);
            MimeMessageHelper messageHelper = new MimeMessageHelper(new MimeMessage(currentMessageTemplate));
             messageHelper.setText(templateEngine.process(currentTemplateName, ctx),true);
            
            return messageHelper;
        } catch (MessagingException ex) {
           throw new RuntimeException("Messaging Exception", ex);
        }
    }
    
}
