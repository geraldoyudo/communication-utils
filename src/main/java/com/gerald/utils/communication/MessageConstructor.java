/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerald.utils.communication;

import java.util.Map;

/**
 *
 * @author Gerald
 */
public interface MessageConstructor<T> {
    public T constructMessage(Map<String,Object> context);
}
