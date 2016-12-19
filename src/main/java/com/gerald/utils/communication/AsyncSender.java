package com.gerald.utils.communication;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;

public interface AsyncSender <T>{
	@Async
	public Future<Void> sendAsync(T message);
}
