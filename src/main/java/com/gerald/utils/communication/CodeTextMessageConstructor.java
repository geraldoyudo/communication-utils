/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import java.util.Map;

import org.springframework.context.MessageSource;

import com.gerald.utils.communication.entities.SMSMessage;

/**
 *
 * @author Gerald
 */
public class CodeTextMessageConstructor implements MessageConstructor<SMSMessage>{
    public String messageCode = "text.message.template";
    public static final String KEY = "code";
    public MessageSource source;
    private String origin = "UMaaS";

    public CodeTextMessageConstructor(MessageSource source) {
        this.source = source;
    }
    
    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public SMSMessage constructMessage(Map<String, Object> context) {
        SMSMessage message = new SMSMessage();
        Object[] objects = new Object[]{context.get(KEY)};
        message.setMessage( source.getMessage(messageCode, objects, null));
        message.setOriginatingPhone(origin);
        return message;
    }

    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    
}
