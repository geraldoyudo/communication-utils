/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication.entities;

import java.io.Serializable;

/**
 *
 * @author Gerald
 */
public class SMSMessage implements Serializable {
    private String destinationPhone;
    private String originatingPhone;
    private String message;

    public SMSMessage() {
    }

    public SMSMessage(String destinationPhone, String originatingPhone, String message) {
        this.destinationPhone = destinationPhone;
        this.originatingPhone = originatingPhone;
        this.message = message;
    }
    
    public String getDestinationPhone() {
        return destinationPhone;
    }

    public void setDestinationPhone(String destinationPhone) {
        this.destinationPhone = destinationPhone;
    }

    public String getOriginatingPhone() {
        return originatingPhone;
    }

    public void setOriginatingPhone(String originatingPhone) {
        this.originatingPhone = originatingPhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
