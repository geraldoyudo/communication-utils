/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import org.springframework.stereotype.Component;

import com.gerald.utils.communication.entities.SMSMessage;

/**
 *
 * @author Gerald
 */
@Component
public class TestSMSSender implements ISMSSender{

    @Override
    public void send(SMSMessage message) {
        System.out.println("Sending SMS Message");
        System.out.println(String.format("%s = %s\n%s = %s\n%s = %s",
                "Origin",message.getOriginatingPhone(),
                "Destination", message.getDestinationPhone(),
                "Message", message.getMessage()));
    }
    
}
