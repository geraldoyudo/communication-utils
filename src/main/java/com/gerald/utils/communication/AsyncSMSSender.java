package com.gerald.utils.communication;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.gerald.utils.communication.entities.SMSMessage;

@Service
@Qualifier("asyncSmsSender")
public class AsyncSMSSender implements ISMSSender , AsyncSender<SMSMessage> {
	@Autowired
	private ISMSSender smsSender;
	
	@Override
	public void send(SMSMessage message) {
		smsSender.send(message);
		
	}
	
	@Override
	public Future<Void> sendAsync(SMSMessage message) {
		send(message);
		return new AsyncResult<Void>(null);
	
	}

}
