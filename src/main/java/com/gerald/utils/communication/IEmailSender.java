/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import javax.mail.internet.MimeMessage;

/**
 *
 * @author Gerald
 */
public interface IEmailSender extends Sender<MimeMessage>{
    
    @Override
    public void send(MimeMessage message);
    
}
