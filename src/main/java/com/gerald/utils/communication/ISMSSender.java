/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import com.gerald.utils.communication.entities.SMSMessage;

/**
 *
 * @author Gerald
 */
public interface ISMSSender extends Sender<SMSMessage>{
    
    @Override
    public void send(SMSMessage message);
   
}
